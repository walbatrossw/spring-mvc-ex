package com.doubles.mvcboard.commons.util;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class UploadFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

    // 파일 업로드 처리 메서드
    public static String uploadFile(String originalFileName, byte[] fileData, HttpServletRequest request) throws Exception {

        // 저장파일명 : 중복 방지 처리
        String uuidFileName = getUuidFileName(originalFileName);

        // 파일 업로드 경로 설정
        String rootPath = getRootPath(originalFileName, request); // 기본경로 추출(이미지 or 일반파일)
        String datePath = getDatePath(rootPath); // 날짜 경로, 날짜 경로 생성처리

        // 서버에 파일 저장
        File target = new File(rootPath + datePath, uuidFileName); // 파일 객체 생성
        FileCopyUtils.copy(fileData, target); // 파일 객체에 파일 데이터 복사

        String filePath = null;

        // 이미지 파일일 경우 썸네일이미지 생성
        if (MediaUtils.getMediaType(getFormatName(originalFileName)) != null) {
            filePath = makeThumbnail(rootPath, datePath, uuidFileName);
            return filePath;
        }

        filePath = getFilePath(rootPath, datePath, uuidFileName);
        return filePath;
    }

    // 파일 삭제
    public static void deleteFile(String fileName, HttpServletRequest request) {

        // 파일 업로드 경로
        String rootPath = getRootPath(fileName, request);

        // 파일타입 확인
        MediaType mediaType = MediaUtils.getMediaType(getFormatName(fileName));

        // 이미지 파일 여부 확인 : 원본이미지 삭제
        if (mediaType != null) {

            String front = fileName.substring(0, 12);
            String end = fileName.substring(14);

            new File(rootPath + (front + end).replace('/', File.separatorChar)).delete();
        }

        // 파일삭제(썸네일이미지 or 일반파일)
        new File(rootPath + fileName.replace('/', File.separatorChar)).delete();
    }

    // 파일 출력을 위한 Header 설정
    public static HttpHeaders getHttpHeaders(String fileName) throws Exception {

        // 파일타입 확인
        MediaType mediaType = MediaUtils.getMediaType(getFormatName(fileName));
        // HttpHeder 객체를 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        // 이미지파일일 경우
        if (mediaType != null) {
            httpHeaders.setContentType(mediaType);
        } else { // 일반파일일 경우
            fileName = fileName.substring(fileName.indexOf("_") + 1); // UUID 제거
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 다운로드 MIME 타입 설정
            // 파일명 한글 인코딩처리
            httpHeaders.add("Content-Disposition", "attachment; filename=\"" +
                    new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
        }

        return httpHeaders;
    }

    // 파일업로드 기본경로 추출 메서드
    public static String getRootPath(String fileName, HttpServletRequest request) {
        if (MediaUtils.getMediaType(getFormatName(fileName)) != null) {
            // 이미지 파일 경로
            return request.getSession().getServletContext().getRealPath("/resources/upload/images");
        }
        // 일반파일 경로
        return request.getSession().getServletContext().getRealPath("/resources/upload/files");
    }

    // 1. 파일명 중복방지 메서드
    private static String getUuidFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }

    // 2. 파일 확장자 추출 메서드
    private static String getFormatName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    // 3. 날짜경로 추출메서드
    private static String getDatePath(String uploadPath) {
        Calendar calendar = Calendar.getInstance();
        // 년도
        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        // 년도/월
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        // 년도/월/일
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
        // 폴더생성
        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    // 4. 폴더 생성 메서드
    private static void makeDir(String uploadPath, String... paths) {

        // 날짜별 경로가 이미 존재하면 메서드 종료
        if (new File(uploadPath + paths[paths.length - 1]).exists())
            return;

        for (String path :  paths) {
            File dirPath = new File(uploadPath + path);
            if (!dirPath.exists())
                dirPath.mkdir();

        }
    }

    // 5. 썸네일 이미지 생성
    private static String makeThumbnail(String uploadRootPath, String datePath, String fileName) throws Exception {
        // 원본이미지를 메모리상에 로딩
        BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
        // 원본이미지를 축소
        BufferedImage thumbnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        // 썸네일파일명 생성
        String fullPath = uploadRootPath + datePath + File.separator + "s_" + fileName;
        // 파일 객체생성
        File newFile = new File(fullPath);
        // 파일 확장자 추출
        String formatName = getFormatName(fileName);
        // 썸네일 파일 저장
        ImageIO.write(thumbnailImg, formatName.toUpperCase(), newFile);

        return fullPath.substring(uploadRootPath.length());
    }

    // 6. 업로드 파일 경로 추출 메서드
    private static String getFilePath(String uploadRootPath, String datePath, String fileName) throws Exception {
        String fullPath = uploadRootPath + datePath + File.separator + fileName;
        return fullPath.substring(uploadRootPath.length());
    }

}

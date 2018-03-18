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

    // 파일 업로드
    public static String uploadFile(String originalFileName, byte[] fileData, HttpServletRequest request) throws Exception {

        // 파일명 중복방지 방지
        UUID uuid = UUID.randomUUID();
        String savedFileName = uuid.toString() + "_" + originalFileName;

        // 파일 업로드 경로 설정
        String uploadRootPath = getUploadRootPath(originalFileName, request);
        String datePath = getDatePath(uploadRootPath);

        // 파일 객체 생성, 파일 복사
        File target = new File(uploadRootPath + datePath, savedFileName);
        FileCopyUtils.copy(fileData, target);

        // 업로드 경로 + 파일명
        String uploadedFileName = null;

        // 이미지 파일일 경우 썸네일이미지 생성
        if (MediaUtils.getMediaType(getFormatName(originalFileName)) != null) {
            uploadedFileName = makeThumbnail(uploadRootPath, datePath, savedFileName);
        } else {
            uploadedFileName = makeIcon(uploadRootPath, datePath, savedFileName);
        }

        return uploadedFileName;
    }

    // 파일 삭제
    public static void deleteFile(String fileName, HttpServletRequest request) {

        // 파일 업로드 경로
        String uploadRootPath = getUploadRootPath(fileName, request);

        // 파일타입 확인
        MediaType mediaType = MediaUtils.getMediaType(getFormatName(fileName));

        // 이미지 파일 여부 확인 : 원본이미지 삭제
        if (mediaType != null) {

            String front = fileName.substring(0, 12);
            String end = fileName.substring(14);

            new File(uploadRootPath + (front + end).replace('/', File.separatorChar)).delete();

        }

        // 파일삭제(썸네일이미지 or 일반파일)
        new File(uploadRootPath + fileName.replace('/', File.separatorChar)).delete();
    }

    // 파일 출력을 위한 Header 설정
    public static HttpHeaders getHttpHeaders(String fileName) throws Exception {

        // 파일타입 확인
        MediaType mediaType = MediaUtils.getMediaType(getFormatName(fileName));

        HttpHeaders httpHeaders = new HttpHeaders();

        if (mediaType != null) {
            httpHeaders.setContentType(mediaType);
        } else {
            fileName = fileName.substring(fileName.indexOf("_") + 1);
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.add("Content-Disposition", "attachment; filename=\"" +
                    new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
        }

        return httpHeaders;
    }

    // 파일 확장자 추출
    public static String getFormatName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    // 파일업로드 루트 경로 추출 : 이미지파일과 일반파일 루트 분리
    public static String getUploadRootPath(String fileName, HttpServletRequest request) {

        if (MediaUtils.getMediaType(getFormatName(fileName)) != null) {
            return request.getSession().getServletContext().getRealPath("/resources/upload/images/");
        }

        return request.getSession().getServletContext().getRealPath("/resources/upload/files/");
    }

    // 날짜별 경로 추출 : 파일 관리를 위해
    private static String getDatePath(String uploadPath) {

        Calendar calendar = Calendar.getInstance();
        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    // 파일 저장 경로 생성
    private static void makeDir(String uploadPath, String... paths) {

        if (new File(uploadPath + paths[paths.length - 1]).exists())
            return;

        for (String path :  paths) {

            File dirPath = new File(uploadPath + path);

            if (!dirPath.exists())
                dirPath.mkdir();

        }
    }

    // 썸네일 이미지 생성
    private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
        File newFile = new File(thumbnailName);
        String formatName = getFormatName(fileName);
        ImageIO.write(destImg, formatName.toUpperCase(), newFile);

        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    // 파일 아이콘
    private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {

        String iconName = uploadPath + path + File.separator +  fileName;

        return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

}

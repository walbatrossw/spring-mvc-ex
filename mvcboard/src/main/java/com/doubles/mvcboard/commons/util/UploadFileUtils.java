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

        UUID uuid = UUID.randomUUID(); // 중복저장을 방지하기 위해
        String savedFileName = uuid.toString() + "_" + originalFileName;

        String fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 파일의 확장자 추출
        String uploadRootPath = getUploadRootPath(fileExt, request);   // 파일 업로드 루트 경로
        String datePath = getDatePath(uploadRootPath);

        File target = new File(uploadRootPath + datePath, savedFileName);
        FileCopyUtils.copy(fileData, target);

        String uploadedFileName = null;

        if (MediaUtils.getMediaType(fileExt) != null) {
            uploadedFileName = makeThumbnail(uploadRootPath, datePath, savedFileName);
        } else {
            uploadedFileName = makeIcon(uploadRootPath, datePath, savedFileName);
        }

        return uploadedFileName;
    }

    // 파일 출력을 위한 Header 설정
    public static HttpHeaders getHttpHeaders(String fileName) throws Exception {

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

        MediaType mediaType = MediaUtils.getMediaType(fileExt);
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

    // 파일 삭제
    public static void deleteFile(String fileName, HttpServletRequest request) {

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadRootPath = getUploadRootPath(fileExt, request);

        MediaType mediaType = MediaUtils.getMediaType(fileExt);

        if (mediaType != null) {

            String front = fileName.substring(0, 12);
            String end = fileName.substring(14);

            new File(uploadRootPath + (front + end).replace('/', File.separatorChar)).delete();

        }

        new File(uploadRootPath + fileName.replace('/', File.separatorChar)).delete();
    }


    // 루트 경로 : 이미지파일과 일반파일 루트 분리
    public static String getUploadRootPath(String fileExt, HttpServletRequest request) {

        if (MediaUtils.getMediaType(fileExt) != null) {
            return request.getSession().getServletContext().getRealPath("/resources/upload/images/");
        }

        return request.getSession().getServletContext().getRealPath("/resources/upload/files/");

    }


    // 날짜 경로 추출 : 파일 관리를 위해
    private static String getDatePath(String uploadPath) {

        Calendar calendar = Calendar.getInstance();

        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    // 디렉토리 생성
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
        String thumnailName = uploadPath + path + File.separator + "s_" + fileName;
        File newFile = new File(thumnailName);
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        ImageIO.write(destImg, formatName.toUpperCase(), newFile);

        return thumnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    // 파일 아이콘
    private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {

        String iconName = uploadPath + path + File.separator +  fileName;

        return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

}

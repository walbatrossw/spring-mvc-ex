package com.doubles.ex04.commons.util;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

// 파일업로드 유틸 클래스
public class UploadFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

    // 파일 업로드 처리
    public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

        // 중복된 파일명 저장을 하지 않기 위해 UUID 키값을 생성
        UUID uuid = UUID.randomUUID();

        // 저장파일명 = UUID + _ + 원본파일명
        String savedName = uuid.toString() + "_" + originalName;

        // 1. 기본 저장 경로 + 날짜별 경로 생성
        String savedPath = calcPath(uploadPath);

        // 기본 저장 경로 + 날짜별 경로 + 파일명 파일 객체 생성
        // File(String parent, String child) : parent 폴더 경로의 child 라는 파일에 대한 File 객체를 생성
        File target = new File(uploadPath + savedPath, savedName);

        // fileData를 target 객체에 복사
        FileCopyUtils.copy(fileData, target);

        // 확장자명 추출
        String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);

        // 업로드 파일명 : 썸네일 이미지 파일명 or 일반 파일명
        String uploadFileName = null;

        // 확장자에 따라 썸네일 생성 or 일반 파일 아이콘 생성
        if (MediaUtils.getMediaType(formatName) != null) {
            // 3. 썸네일 생성
            uploadFileName = makeThumbnail(uploadPath, savedPath, savedName);
        } else {
            // 4. 아이콘 생성
            uploadFileName = makeIcon(uploadPath, savedPath, savedName);
        }

        return uploadFileName;
    }

    // 1. 파일 저장을 위한 날짜 경로 생성
    private static String calcPath(String uploadPath) {

        Calendar calendar = Calendar.getInstance();

        // 년 : /2017
        String yearPath = File.separator + calendar.get(Calendar.YEAR);

        // 년 + 월 : /2017/12
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);

        // /년 + 월 + 일 : /2017/12/01
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        // 2. 기본 파일 저장 경로 + 날짜 경로 생성
        makeDir(uploadPath, yearPath, monthPath, datePath);

        logger.info(datePath);

        return datePath;
    }

    // 2. 파일 저장 경로 생성 + 날짜 경로 생성
    private static void makeDir(String uploadPath, String... paths) {

        // 날짜 경로가 이미 존재 O : 메서드 종료
        if (new File(uploadPath + paths[paths.length - 1]).exists()) {
            return;
        }

        // 날짜 경로가 존재 X : 경로 생성
        for (String path : paths) {
            // 기본 경로 + 날짜 경로에 해당하는 파일객체 생성
            File dirPath = new File(uploadPath + path);
            // 파일 경로가 존재 X
            if (!dirPath.exists()) {
                // 경로 생성
                dirPath.mkdir();
            }
        }

    }

    // 3. 썸네일 생성 : 이미지 파일 O
    private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

        // BufferedImage : 실제 이미지 X, 메모리상의 이미지를 의미하는 객체

        // 원본 파일을 메모리상에 로딩
        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

        // 정해진 크기에 맞게 작은 이미지 파일에 원본이미지를 복사
        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

        // 썸네일 이미지 파일명 생성
        String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

        // 썸네일 파일 객체 생성
        File newFile = new File(thumbnailName);

        // 파일 확장자명 추출
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 썸네일 파일을 저장
        ImageIO.write(destImg, formatName.toUpperCase(), newFile);

        // 썸네일 파일의 경로 + 파일명 반환
        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    // 4. 아이콘 생성 : 이미지 파일 X
    private static String makeIcon(String uploadPath, String savedPath, String fileName) throws Exception {
        // 아이콘 파일명 = 저장경로 + 날짜경로 + 구분자 + 파일명
        String iconName = uploadPath + savedPath + File.separator + fileName;
        return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

}

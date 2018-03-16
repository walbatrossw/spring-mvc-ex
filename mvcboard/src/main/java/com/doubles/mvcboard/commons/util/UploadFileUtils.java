package com.doubles.mvcboard.commons.util;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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



    public static String uploadFile(String originalFileName, byte[] fileData, HttpServletRequest request) throws Exception {

        UUID uuid = UUID.randomUUID();

        String uploadPath = getUploadPath(request);
        String savedFileName = uuid.toString() + "_" + originalFileName;
        String savedPath = getDatePath(uploadPath);

        File target = new File(uploadPath + savedPath, savedFileName);
        FileCopyUtils.copy(fileData, target);

        String formatName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String uploadedFileName = null;

        if (MediaUtils.getMediaType(formatName) != null) {
            uploadedFileName = makeThumbnail(uploadPath, savedPath, savedFileName);
        } else {
            uploadedFileName = makeIcon(uploadPath, savedPath, savedFileName);
        }

        return uploadedFileName;
    }

    // 업로드 경로
    public static String getUploadPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/resources/upload/");
    }

    // 날짜 저장경로 추출
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

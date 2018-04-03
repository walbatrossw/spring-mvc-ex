package com.doubles.mvcboard.commons.util;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

class MediaUtils {

    private static Map<String, MediaType> mediaTypeMap;

    // 클래스 초기화 블럭
    static {
        mediaTypeMap = new HashMap<>();
        mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
        mediaTypeMap.put("PNG", MediaType.IMAGE_PNG);
    }

    // 파일 타입
    static MediaType getMediaType(String fileName) {
        String formatName = getFormatName(fileName);
        return mediaTypeMap.get(formatName);
    }

    // 파일 확장자 추출
    static String getFormatName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
    }

}

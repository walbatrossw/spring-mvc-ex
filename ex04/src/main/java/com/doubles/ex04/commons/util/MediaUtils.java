package com.doubles.ex04.commons.util;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

// 이미지 파일 유무 검토 클래스
public class MediaUtils {

    private static Map<String, MediaType> mediaMap;

    static {
        mediaMap = new HashMap<>();
        mediaMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaMap.put("GIF", MediaType.IMAGE_GIF);
        mediaMap.put("PNG", MediaType.IMAGE_PNG);
    }

    public static MediaType getMediaType(String type) {
        return mediaMap.get(type.toUpperCase());
    }
}

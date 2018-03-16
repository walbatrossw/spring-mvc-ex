package com.doubles.mvcboard.commons.util;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MediaUtils {

    private static Map<String, MediaType> mediaTypeMap;

    static {
        mediaTypeMap = new HashMap<>();
        mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
        mediaTypeMap.put("PNG", MediaType.IMAGE_PNG);
    }


    public static MediaType getMediaType(String type) {
        return mediaTypeMap.get(type.toUpperCase());
    }

}

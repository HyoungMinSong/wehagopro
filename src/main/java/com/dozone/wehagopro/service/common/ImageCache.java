package com.dozone.wehagopro.service.common;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

public class ImageCache {

        private static final Map<String, byte[]> imageMap = new HashMap<>();

        public static void addImage(String imageName, byte[] imageBytes) {
            System.out.println("캐시 add : "+imageName+" : "+imageBytes);
            imageMap.put(imageName, imageBytes);
        }

        public static byte[] getImage(String imageName) {
            return imageMap.get(imageName);
        }

        public static void removeImage(String imageName) {
            imageMap.remove(imageName);
        }


    // ImageCache의 getImage 메서드를 ByteArrayResource로 변환
    public static Resource getImageResource(String imageName) {
        byte[] imageBytes = getImage(imageName);
        if (imageBytes != null) {
            System.out.println("반환 성공");
            return new ByteArrayResource(imageBytes);
        } else {
            System.out.println("반환 실패");
            return null; // 이미지가 캐시에 없는 경우 null 반환
        }
    }
    }

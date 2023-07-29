package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class PhotoDto {

    private String photo_name;
    private String photo_path;

    public PhotoDto() {
    }

    public PhotoDto(String photo_name, String photo_path) {
        this.photo_name = photo_name;
        this.photo_path = photo_path;
    }
}

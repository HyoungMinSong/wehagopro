package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class ShortLinkLoginDto {
    private String shortLink;
    private Date shortLinkDeadLine;
    private int empNo;
    private int a;
}

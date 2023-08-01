package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

import java.sql.Date;

@Data
public class ShortLinkSignUpDto {
    private String shortLink;
    private Date shortLinkDeadLine;
    private int empNo;
}

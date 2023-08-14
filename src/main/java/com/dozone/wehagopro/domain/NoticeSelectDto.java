package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class NoticeSelectDto {
    private int t_notice_no;
    private String t_user_name;
    private int t_company_no;
    private String t_notice_title;
    private String t_notice_content;
    private Date t_notice_date;
    private int t_notice_state;
    private Date t_notice_update_date;
    private Date t_notice_delete_date;

    }
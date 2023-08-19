package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class LogDto {
    private Integer t_log_no;
    private Integer t_employee_no;
    private String t_log_type;
    private Date t_log_date;
    private String t_log_content;
    private Integer t_log_state;
}

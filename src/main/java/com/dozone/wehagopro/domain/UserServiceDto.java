package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceDto {
    private String t_employee_no;
    private String t_service_name;
    private String t_service_path;
    private String t_service_main_icon_path;
    private String t_service_description;
}

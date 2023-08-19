package com.dozone.wehagopro.repository.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogMapper {

    void saveLog(@Param("t_employee_no") Integer t_employee_no, @Param("t_log_type") String t_log_type, @Param("t_log_content") String t_log_content);

}

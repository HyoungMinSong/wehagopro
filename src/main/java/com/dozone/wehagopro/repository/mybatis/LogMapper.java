package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.LogDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper {

    void insertLog(@Param("t_employee_no") Integer t_employee_no, @Param("t_log_type") String t_log_type, @Param("t_log_content") String t_log_content);

    List<LogDto> selectLog(Integer t_employee_no);

    void updateLog(Integer t_employee_no);

    void deleteLog(Integer t_employee_no);

    List<Integer> selectEmployee(Integer t_company_no);

    String selectServiceName(Integer t_service_no);

}

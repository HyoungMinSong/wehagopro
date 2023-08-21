package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.LogDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper {

    void saveLog(@Param("t_employee_no") Integer t_employee_no, @Param("t_log_type") String t_log_type, @Param("t_log_content") String t_log_content);

    List<LogDto> findLogByEmployee(Integer t_employee_no);

    void updateLogByEmployee(Integer t_employee_no);

    void deleteLogByEmployee(Integer t_employee_no);

    List<Integer> findEmployeeByCompany(Integer t_company_no);

    String findServiceNameByNo(Integer t_service_no);

}

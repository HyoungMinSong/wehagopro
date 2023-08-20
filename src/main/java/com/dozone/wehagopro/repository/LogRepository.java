package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.LogDto;
import com.dozone.wehagopro.repository.mybatis.LogMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogRepository {
    private final LogMapper logMapper;

    public void saveLog(Integer t_employee_no, String t_log_type, String t_log_content){
        logMapper.saveLog(t_employee_no, t_log_type, t_log_content);
    };

    public List<LogDto> findLogByEmployee(Integer t_employee_no){
        return logMapper.findLogByEmployee(t_employee_no);
    }

    public void updateLogByEmployee(Integer t_employee_no){
        logMapper.updateLogByEmployee(t_employee_no);
    }

    public void deleteLogByEmployee(Integer t_employee_no){
        logMapper.deleteLogByEmployee(t_employee_no);
    }

    public List<Integer> findEmployeeByCompany(Integer t_company_no){
        return logMapper.findEmployeeByCompany(t_company_no);
    }

    public String findServiceNameByNo(Integer t_service_no){
        return logMapper.findServiceNameByNo(t_service_no);
    }

}

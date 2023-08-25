package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.LogDto;
import com.dozone.wehagopro.repository.mybatis.LogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogRepository {
    private final LogMapper logMapper;

    public void insertLog(Integer t_employee_no, String t_log_type, String t_log_content){
        logMapper.insertLog(t_employee_no, t_log_type, t_log_content);
    };

    public List<LogDto> selectLog(Integer t_employee_no){
        return logMapper.selectLog(t_employee_no);
    }

    public void updateLog(Integer t_employee_no){
        logMapper.updateLog(t_employee_no);
    }

    public void deleteLog(Integer t_employee_no){
        logMapper.deleteLog(t_employee_no);
    }

    public List<Integer> selectEmployee(Integer t_company_no){
        return logMapper.selectEmployee(t_company_no);
    }

    public String selectServiceName(Integer t_service_no){
        return logMapper.selectServiceName(t_service_no);
    }

}

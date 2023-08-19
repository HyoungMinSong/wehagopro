package com.dozone.wehagopro.repository;

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

}

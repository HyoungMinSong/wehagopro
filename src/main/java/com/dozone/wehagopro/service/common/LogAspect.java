package com.dozone.wehagopro.service.common;

import com.dozone.wehagopro.domain.OrganizationSelectedDto;
import com.dozone.wehagopro.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogRepository logRepository; // MyBatis 로그 저장용 Repository

    @After("@annotation(Loggable)") // Loggable 어노테이션이 붙은 메서드 호출 전에 실행
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("methodName : "+methodName);
        for(int i=0; i< args.length; i++) {
            System.out.println("args["+i+"] : " + args[i]);
        }

        if(methodName == "updateEmployeeState" && (int) args[0] != -1){
            // args[1]은 OrganizationSelectedDto 객체이므로 적절한 캐스팅을 수행합니다.
            OrganizationSelectedDto dto = (OrganizationSelectedDto)((ArrayList<OrganizationSelectedDto>) args[1]).get(0);
            int t_employee_no = dto.getT_employee_no();
            int t_employee_state = (int) args[0];
            String t_log_content = "";
            switch (t_employee_state){
                case 2 :
                    t_log_content += "사용중지가 해제";
                case 3 :
                    t_log_content += "사용중지 ";
                default:
                    t_log_content += "되었습니다.";
                    break;
            }
            String t_log_type = "조직관리";
            logRepository.saveLog(t_employee_no, t_log_type, t_log_content);
        }

    }

}

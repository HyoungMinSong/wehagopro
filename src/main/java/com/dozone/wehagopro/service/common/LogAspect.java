package com.dozone.wehagopro.service.common;

import com.dozone.wehagopro.domain.LogDto;
import com.dozone.wehagopro.domain.NoticeDto;
import com.dozone.wehagopro.domain.OrganizationEmplRegiDTO;
import com.dozone.wehagopro.domain.OrganizationSelectedDto;
import com.dozone.wehagopro.repository.LogRepository;
import com.dozone.wehagopro.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogRepository logRepository; // MyBatis 로그 저장용 Repository
    @Autowired
    private OrganizationService organizationService;

    @After("@annotation(Loggable)") // Loggable 어노테이션이 붙은 메서드 호출 전에 실행
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

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
        if(methodName == "createNotice"){
            int t_company_no = ((NoticeDto) args[0]).getT_company_no();
            List<Integer> employeeList = organizationService.findEmployeeByCompany(t_company_no);
            String t_log_type = "공지사항";
            String t_log_content = "새로운 공지사항이 작성되었습니다.";
            for(int t_employee_no : employeeList){
                logRepository.saveLog(t_employee_no, t_log_type, t_log_content);
            }
        }
        if(methodName == "modifyRoomForAOldEmployee"){
            int t_employee_no = ((OrganizationEmplRegiDTO)args[0]).getT_employee_no();
            String t_log_type = "조직관리";
            String t_log_content = "직원 정보가 수정되었습니다.";
            logRepository.saveLog(t_employee_no, t_log_type, t_log_content);
        }
        if(methodName == "saveInvitedEmployeePublish"){
            int t_employee_no = (int)args[0];
            String t_log_type = logRepository.findServiceNameByNo((Integer)args[1]);
            String t_log_content = "서비스가 배포되었습니다.";
            logRepository.saveLog(t_employee_no, t_log_type, t_log_content);
        }
        if(methodName == "updateUnPublish"){
            int t_employee_no = (int)args[0];
            String t_log_type = logRepository.findServiceNameByNo((Integer)args[1]);
            String t_log_content = "서비스가 배포 해제되었습니다.";
            logRepository.saveLog(t_employee_no, t_log_type, t_log_content);
        }

    }



}

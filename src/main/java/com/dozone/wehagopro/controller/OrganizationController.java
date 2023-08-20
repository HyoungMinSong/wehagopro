package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.service.OrganizationService;
import com.dozone.wehagopro.service.common.ImageCache;
import com.dozone.wehagopro.service.common.LogAspect;
import com.dozone.wehagopro.service.common.MailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;
    @Autowired
    MailService mailService;

    // 직원 목록 유저,직원,부서이름 from 회사번호
    @GetMapping("/findUserEmplOrgaFromCompany")
    public List<OrganizationUserEmplDto> findUserEmplOrgaFromCompany(Integer t_company_no){
        return organizationService.findUserEmplOrgaFromCompany(t_company_no);
    };
    // 부서 목록 from 회사번호
    @GetMapping("/findOrganizationFromCompany")
    public List<OrganizationDto> findOrganizationFromCompany(Integer t_company_no){
        return organizationService.findOrganizationFromCompany(t_company_no);
    };

    @GetMapping(value = "/images/{imageName:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Resource getImage(@PathVariable String imageName) throws IOException {
        System.out.println("요청왔다" + imageName);
        return organizationService.getImage(imageName);
    }

    // 이미지 삭제
    @PostMapping("/deleteEmployeePhoto")
    public ResponseEntity<String> deleteEmployeePhoto(@RequestBody String fileName) {
        return organizationService.deleteEmployeePhoto(fileName);
    }

    // 이미지 저장
    @PostMapping("/uploadEmployeePhoto")
    public PhotoDto uploadEmployeePhoto(@RequestParam("file")MultipartFile file){
        return organizationService.uploadEmployeePhoto(file);
    }

    // 이메일 중복확인
    @GetMapping("/checkRegister")
    public int checkRegister(String t_user_email, String t_user_phone){
        System.out.println("t_"+t_user_email+"  "+t_user_phone);
        return organizationService.checkRegister(t_user_email, t_user_phone);
    }

    // 직원 등록
    @PostMapping("/makeRoomForANewEmployee")
    public void makeRoomForANewEmployee(@RequestBody OrganizationEmplRegiDTO dto){
        System.out.println("dto : "+dto);
        organizationService.makeRoomForANewEmployee(dto);
    }

    // 직원 수정
    @PostMapping("/modifyRoomForAOldEmployee")
    public void modifyRoomForAOldEmployee(@RequestBody OrganizationEmplRegiDTO dto){
        System.out.println("dto : "+dto);
        organizationService.modifyRoomForAOldEmployee(dto);
    }

    // 조직도 부서 수정
    @PostMapping("/editingOrganization")
    public void editingOrganization(@RequestBody List<OrganizationEditDTO> dto){
        List<OrganizationEditDTO> insertDto = new ArrayList<>();
        List<OrganizationEditDTO> updateDto = new ArrayList<>();
        List<OrganizationEditDTO> deleteDto = new ArrayList<>();

        for(OrganizationEditDTO dt : dto) {
            System.out.println(dt);
            if(dt.getT_organization_no()!=null && dt.getT_organization_name()==null){
                System.out.println("삭제"+dt.getT_organization_no());
                deleteDto.add(dt);
            }else if(dt.getT_organization_no()==null && dt.getT_organization_name()!=null){
                System.out.println("추가"+dt.getT_organization_name());
                insertDto.add(dt);
            }else if(dt.getT_organization_no()!=null && dt.getT_organization_name()!=null){
                System.out.println("수정"+dt.getT_organization_name());
                updateDto.add(dt);
            }
        }

        organizationService.editingOrganization(insertDto, updateDto, deleteDto);
    }

    // 메일
    @PostMapping("/sendMailToEmployee")
    public void sendMailToEmployee(@RequestBody OrganizationMailDto dto) throws MessagingException {
        mailService.sendMailToEmployee(dto.getEmployer(), dto.getCheckedEmployee());
    }

    // 하단 바 체크한 직원 삭제
    @PutMapping("/updateEmployeeState")
    public void updateEmployeeState(@RequestBody OrganizationStateDto dto){
        organizationService.updateEmployeeState(dto.getT_employee_state(), dto.getCheckedEmployee());
    }

    // 로그 목록
    @GetMapping("/findLogByEmployee")
    public List<LogDto> findLogByEmployee(Integer t_employee_no){
        return organizationService.findLogByEmployee(t_employee_no);
    }

    @PutMapping("/updateLogByEmployee")
    public void updateLogByEmployee(@RequestBody Integer t_employee_no){
        organizationService.updateLogByEmployee(t_employee_no);
    }

    @PutMapping("/deleteLogByEmployee")
    public void deleteLogByEmployee(@RequestBody Integer t_employee_no){
        organizationService.deleteLogByEmployee(t_employee_no);
    }



}

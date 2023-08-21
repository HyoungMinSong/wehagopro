package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.repository.LogRepository;
import com.dozone.wehagopro.repository.OrganizationRepository;
import com.dozone.wehagopro.service.common.ImageCache;
import com.dozone.wehagopro.service.common.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private final OrganizationRepository organizationRepository;
    @Autowired
    private final ResourceLoader resourceLoader;
    @Autowired
    private LogRepository logRepository;

    public OrganizationService(OrganizationRepository organizationRepository, ResourceLoader resourceLoader) {
        this.organizationRepository = organizationRepository;
        this.resourceLoader = resourceLoader;
    }

    // 프로젝트의 상대 경로에 대한 변수
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";


    // 직원 목록 유저,직원,부서이름 from 회사번호
    public List<OrganizationUserEmplDto> findUserEmplOrgaFromCompany(Integer t_company_no){
        List<OrganizationUserEmplDto> employeeList;
        employeeList = organizationRepository.findUserEmplOrgaFromCompany(t_company_no);
        // 각 객체의 t_user_photo_path에 String 추가
        employeeList.forEach(employee -> {
            if(!employee.getT_user_photo_path().contains("https")) {
                String originalPath = employee.getT_user_photo_path();
                String modifiedPath = "http://localhost:8080/images/" + originalPath; // 여기서 "추가할 String"에 원하는 String 값을 넣으시면 됩니다.
                employee.setT_user_photo_path(modifiedPath);
            }
        });
        return employeeList;
    };
    // 부서 목록 from 회사번호
    public List<OrganizationDto> findOrganizationFromCompany(Integer t_company_no){
        return organizationRepository.findOrganizationFromCompany(t_company_no);
    };

    // 이미지 출력
    public Resource getImage(String imageName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/images/" + imageName);
        if (resource.exists()) {
            return resource;
        } else {
            // 이미지가 존재하지 않을 경우 에러 처리
            // 예: return resourceLoader.getResource("classpath:static/images/not_found.jpg");
            System.out.println("아직 모른다");
            return ImageCache.getImageResource(imageName);
        }
    }

    // 조직도 부서 수정
    @Transactional
    public void editingOrganization(List<OrganizationEditDTO> insertDto, List<OrganizationEditDTO> updateDto, List<OrganizationEditDTO> deleteDto) {
        // 조직도 부서 추가
        for (OrganizationEditDTO dto : insertDto) {
            organizationRepository.createOrganization(dto.getT_organization_name(), dto.getT_organization_parent());
        }
        // 조직도 부서 수정
        for (OrganizationEditDTO dto : updateDto) {
            organizationRepository.updateOrganization(dto.getT_organization_name(), dto.getT_organization_no());
        }
        // 조직도 부서 삭제
        for (OrganizationEditDTO dto : deleteDto) {
            organizationRepository.deleteOrganization(dto.getT_organization_no());
        }
    }

    // 사진 삭제
    public ResponseEntity<String> deleteEmployeePhoto(String fileName){
        // 따옴표 제거
        fileName = fileName.replace("\"", "");
        System.out.println("fileName : "+fileName);
        try {
            // 이미지 경로를 상대 경로에서 절대 경로로 변환합니다.
            Path uploadDir = Paths.get(UPLOAD_DIR).toAbsolutePath();
            Path filePath = uploadDir.resolve(fileName);
            System.out.println("filePath : "+filePath);
            // 파일이 존재하는지 확인하고, 존재하면 삭제합니다.
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                // ImageCache에서도 해당 이미지를 삭제합니다. (옵션)
//                ImageCache.removeImage(fileName);
                System.out.println("파일 존재");
                return ResponseEntity.ok("Image deleted successfully.");
            } else {
                System.out.println("파일 없음");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found.");
            }
        } catch (Exception e) {
            System.out.println("에러 : "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the image.");
        }
    };

    // 사진 등록
    public PhotoDto uploadEmployeePhoto(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("왜 없지?");
            return null;
        }

        try {
            // 파일이름은 현재 시간과 원래 파일 이름을 조합하여 고유한 이름으로 저장합니다.
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            // 상대 경로를 절대 경로로 변환합니다.
            Path uploadDir = Paths.get(UPLOAD_DIR).toAbsolutePath();
            Path filePath = uploadDir.resolve(fileName);
            System.out.println("filePath @@@@@@@@@@@@@@@@@ : " + filePath);
            // 이미지 파일을 저장합니다.
            file.transferTo(filePath.toFile());

            // 저장한 이미지 파일의 바이트 배열을 가져옵니다.
            byte[] imageBytes = Files.readAllBytes(filePath);
            // ImageCache에 이미지를 추가합니다.
            ImageCache.addImage(fileName, imageBytes);

            PhotoDto dto = new PhotoDto(fileName, fileName);
            // 이미지 파일이 저장된 경로를 클라이언트에게 응답합니다.
            return dto;
        } catch (
                IOException e) {
            System.out.println("뭐가문제 " + e);
            return null;
        }
    }

    // 이메일, 폰 중복 확인
    public int checkRegister(String t_user_email, String t_user_phone){
        int emailNum = organizationRepository.checkRegisterEmail(t_user_email);
        int phoneNum = organizationRepository.checkRegisterPhone(t_user_phone);
        if(phoneNum >0){
            return 1;
        }else if(emailNum >0){
            return 2;
        }else{
            return 0;
        }
    }

    // 직원 등록
    @Transactional
    public void makeRoomForANewEmployee(OrganizationEmplRegiDTO dto){
        String longLink =  "/signup/invite?"
            +"t_user_name="+dto.getT_user_name()
            +"&t_user_phone="+dto.getT_user_phone()
            +"&t_user_email="+dto.getT_user_email();
        System.out.println("longlink"+longLink);
        dto.setT_shortlink_link(longLink);
        if(dto.getT_organization_no() == -1){
            int c = organizationRepository.findCompanyOrgaNoFromName(dto.getT_organization_name());
            dto.setT_organization_no(c);
        }
        int a = organizationRepository.registerUser(dto);
        dto.setT_user_no(a);
        int b = organizationRepository.registerEmployee(dto);
        dto.setT_employee_no(b);
        organizationRepository.createShortLink(dto);
    }

    // 직원 수정
    @Loggable
    @Transactional
    public void modifyRoomForAOldEmployee(OrganizationEmplRegiDTO dto){
        if(dto.getT_user_photo_path() != dto.getT_user_photo_path_prev()){
            if(dto.getT_user_photo_path().contains("http:")){
                String[] pathSegments = dto.getT_user_photo_path().split("/");
                String fileName = pathSegments[pathSegments.length - 1];
                dto.setT_user_photo_path(fileName);
            }
            organizationRepository.updateDetailUser(dto);
        }
        if(dto.getT_organization_no() == -1){
            int c = organizationRepository.findCompanyOrgaNoFromName(dto.getT_organization_name());
            dto.setT_organization_no(c);
        }
        organizationRepository.updateDetailEmployee(dto);
    }
    @Loggable
    @Transactional
    public void updateEmployeeState(Integer t_employee_state, List<OrganizationSelectedDto> dto){
        for(OrganizationSelectedDto dt : dto){
            if(t_employee_state == -1){
                organizationRepository.updateFiredPublish(dt.getT_employee_no());
            }
            organizationRepository.updateEmployeeState(t_employee_state, dt.getT_employee_no());
        }
    }

    public List<LogDto> findLogByEmployee(Integer t_employee_no){
        return logRepository.findLogByEmployee(t_employee_no);
    }

    public void updateLogByEmployee(Integer t_employee_no){
        logRepository.updateLogByEmployee(t_employee_no);
    }

    public void deleteLogByEmployee(Integer t_employee_no){
        logRepository.deleteLogByEmployee(t_employee_no);
    }

    public List<Integer> findEmployeeByCompany(Integer t_company_no){
        return logRepository.findEmployeeByCompany(t_company_no);
    }

}

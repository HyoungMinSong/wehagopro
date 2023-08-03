package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.repository.OrganizationRepository;
import com.dozone.wehagopro.service.common.ImageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public OrganizationService(OrganizationRepository organizationRepository, ResourceLoader resourceLoader) {
        this.organizationRepository = organizationRepository;
        this.resourceLoader = resourceLoader;
    }

    // 프로젝트의 상대 경로에 대한 변수
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";


    // 조직도 목록
    public List<OrganizationInitCompDTO> showMyWorkPlace(Integer t_user_no) {
        return organizationRepository.showMyWorkPlace(t_user_no);
    }

    // 조직도 회사 정보
    public List<OrganizationCompInfoDTO> showMyCompanyInfo(Integer t_user_no){
        return organizationRepository.showMyCompanyInfo(t_user_no);
    }

    // 조직도 직원 상태
    public OrganizationInitEmplDTO showMyEmployeeState(Integer pk, Integer index) {
        if(index == -1) {
            return organizationRepository.showMyEmployeeStateFromCompany(pk);
        }else{
            return organizationRepository.showMyEmployeeStateFromOrganization(pk);
        }
    }

    // 조직도 직원 목록 (회사 or 부서 선택)
    public List<OrganizationEmplInfoDTO> showMyEmployees(String nodeName, Integer pk, Integer index, Integer t_employee_state) {
        List<OrganizationEmplInfoDTO> employeeList;
        if (index == -1) {
            employeeList = organizationRepository.showMyEmployeeFromCompany(nodeName, pk, t_employee_state);
        } else {
            employeeList =  organizationRepository.showMyEmployeeFromOrganization(nodeName, pk, t_employee_state);
        }
        Path uploadDir = Paths.get(UPLOAD_DIR).toAbsolutePath();
        System.out.println("uploadDir : "+uploadDir);
        System.out.println("uploadDir.toString()"+uploadDir.toString());
        // 각 객체의 t_user_photo_path에 String 추가
        employeeList.forEach(employee -> {
            if(!employee.getT_user_photo_path().contains("https")) {
                String originalPath = employee.getT_user_photo_path();
                String modifiedPath = "http://localhost:8080/images/" + originalPath; // 여기서 "추가할 String"에 원하는 String 값을 넣으시면 됩니다.
                employee.setT_user_photo_path(modifiedPath);
            }
        });

        return employeeList;
    }

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
            organizationRepository.createOrganization(dto.getT_organization_name(), dto.getT_company_no());
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

    // 직원 등록
    @Transactional
    public void makeRoomForANewEmployee(OrganizationEmplRegiDTO dto){
        String longLink =  "/signup/invite?"
            +"t_user_name="+dto.getT_user_name()
            +"&t_user_phone="+dto.getT_user_phone()
            +"&t_user_email="+dto.getT_user_email();
        System.out.println("longlink"+longLink);
        dto.setT_shortlink_link(longLink);
        int a = organizationRepository.registerUser(dto);
        dto.setT_user_no(a);
        int b = organizationRepository.registerEmployee(dto);
        dto.setT_employee_no(b);
        organizationRepository.createShortLink(dto);
    }

    // 직원 수정
    @Transactional
    public void modifyRoomForAOldEmployee(OrganizationEmplRegiDTO dto){
        if(dto.getT_user_photo_path() != dto.getT_user_photo_path_prev()){
            organizationRepository.updateDetailUser(dto);
        }
        organizationRepository.updateDetailEmployee(dto);
    }

}

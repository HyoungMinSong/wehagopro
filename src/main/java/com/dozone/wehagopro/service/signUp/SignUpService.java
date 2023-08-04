package com.dozone.wehagopro.service.signUp;

import com.dozone.wehagopro.domain.signUp.*;
import com.dozone.wehagopro.repository.signUp.SignUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SignUpService {

    @Autowired
    SignUpRepository repository;
    private final PasswordEncoder passwordEncoder;

    public String checkId(String id) {
        System.out.println("serviceid = " + id);
        return repository.idCheck(id);
    }

    @Transactional
    public void signUpInsert(SignUpDto dto) {
        System.out.println("서비스");
        System.out.println("dto = " + dto);
        String name = dto.getName();
        String phoneNumber = dto.getPhoneNumber();
        String id = dto.getId();
        String password = passwordEncoder.encode(dto.getPassword()); // 비밀번호 암호화
        String email = dto.getEmail();
        String companyName = dto.getCompanyName();
        String businessRegistrationNumber = dto.getBusinessRegistrationNumber();
        String businessType = dto.getBusinessType();
        String businessStatus = dto.getBusinessStatus();
        String businessCategory = dto.getBusinessCategory();
        String representativeName = dto.getRepresentativeName();
        String companyPhoneNumber = dto.getCompanyPhoneNumber();
        int packageNo = dto.getPackageNo();
        int packageCount = dto.getPackageCount();
        UserSignUpDto userSignUpDto = new UserSignUpDto(id, password, name, phoneNumber, email);
        repository.signUpUser(userSignUpDto);
        CompanySignUpDto companySignUpDto = new CompanySignUpDto(companyName, packageNo, businessRegistrationNumber, businessType, businessStatus, businessCategory, representativeName, companyPhoneNumber, packageCount);
        repository.signUpCompany(companySignUpDto);
        OrganizationSignUpDto organizationSignUpDto = new OrganizationSignUpDto(companyName);
        repository.signUpOrganization(organizationSignUpDto);
        EmployeeSignUpDto employeeSignUpDto = new EmployeeSignUpDto(userSignUpDto.getNo(), companySignUpDto.getNo(), 0, organizationSignUpDto.getNo());
        repository.signUpEmployee(employeeSignUpDto);
        int employeeSignUpDtoNo = employeeSignUpDto.getNo();
        List<Integer> serviceNo = repository.findServiceNoByPackageNo(packageNo);
        for (int a:
        serviceNo) {
            System.out.println("a = " + a);
        }
        List<Integer> serviceNoByServiceFree = repository.findServiceNoByServiceFree();
        for (int a:
                serviceNoByServiceFree) {
            System.out.println("a = " + a);
        }
        serviceNo.addAll(serviceNoByServiceFree);
        for (int num:
        serviceNo) {
            System.out.println("num = " + num);
            repository.signUpServicePublished(employeeSignUpDtoNo, num);
        }

    }

    public String checkCompanyName(String companyName) {
        System.out.println("servicecompanyName = " + companyName);
        return repository.companyCheck(companyName);
    }

    public ShortLinkSignUpDto findRedirectLink(int empNo){
        System.out.println("serviceEmpNo = " + empNo);
        return repository.findShortLinkByEmpNo(empNo);
    }

    public Integer employeeStateCheck(int empNo){
        System.out.println("serviceEmpNo = " + empNo);
        return repository.employeeStateCheck(empNo);
    }

    @Transactional
    public void signUpInviteUpdate(SignUpInviteUpdateDto dto){
        System.out.println("dto = " + dto);
        int empNo = dto.getEmpNo();
        Integer integerUserNo = repository.findUserNoByEmployeeNo(dto);
        int userNo = integerUserNo.intValue();
        dto.setUserNo(userNo);
        repository.updateEmployeeStateTo2(dto);
        repository.updateInvitedUser(dto);

    }

    public List<CompanyServiceListDto> companyServiceList(int comNo){
        System.out.println("comNo = " + comNo);
        return repository.companyServiceList(comNo);
    }

    public Integer findPackageCountByCompanyNo(int comNo){
        System.out.println("comNo = " + comNo);
        return repository.findPackageCountByCompanyNo(comNo);
    }

    public List<CountPublishedServiceAndEmpNoDto> eachCompanyPublishedCount(List<CompanyServiceListDto> companyServiceListDto, int comNo) {
        List<CountPublishedServiceAndEmpNoDto> listDto = new ArrayList<CountPublishedServiceAndEmpNoDto>();
        for (CompanyServiceListDto dto : companyServiceListDto) {
            CountPublishedServiceAndEmpNoDto countPublishedServiceAndEmpNoDto = repository.eachCompanyPublishedCount(dto.getServiceNo(), comNo);
            listDto.add(countPublishedServiceAndEmpNoDto);
        }

        return listDto;
    }

    public List<UnPublishedUserDto> findUnPublishedUser(int comNo, int serviceNo){
        System.out.println("comNo = " + comNo + ", serviceNo = " + serviceNo);
        return repository.findUnPublishedUser(comNo, serviceNo);
    }

    public void saveInvitedEmployeePublish(int empNo, int serviceNo){
        System.out.println("empNo = " + empNo + ", serviceNo = " + serviceNo);
        repository.signUpServicePublished(empNo,serviceNo);
    }

    public List<UnPublishedUserDto> findPublishedUser(int comNo, int serviceNo){
        System.out.println("comNo = " + comNo + ", serviceNo = " + serviceNo);
        return repository.findPublishedUser(comNo, serviceNo);
    }

    public void updateUnPublish(int empNo, int serviceNo){
        System.out.println("empNo = " + empNo + ", serviceNo = " + serviceNo);
        repository.updateUnPublish(empNo,serviceNo);
    }
}

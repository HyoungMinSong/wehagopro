package com.dozone.wehagopro.service.signUp;

import com.dozone.wehagopro.domain.signUp.*;
import com.dozone.wehagopro.repository.signUp.SignUpRepository;
import com.dozone.wehagopro.service.common.Loggable;
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

    public String findCheckId(String id) {
        System.out.println("serviceid = " + id);
        return repository.selectIdCheck(id);
    }

    public String findEmailCheck(String email){
        System.out.println("email = " + email);
        return repository.selectEmailCheck(email);
    }

    public String findPhoneNumberCheck(String phoneNumber){
        System.out.println("phoneNumber = " + phoneNumber);
        return repository.selectPhoneNumberCheck(phoneNumber);
    }

    @Transactional
    public void addSignUp(SignUpDto dto) {
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
        repository.insertUser(userSignUpDto);
        CompanySignUpDto companySignUpDto = new CompanySignUpDto(companyName, packageNo, businessRegistrationNumber, businessType, businessStatus, businessCategory, representativeName, companyPhoneNumber, packageCount);
        repository.insertCompany(companySignUpDto);
        OrganizationSignUpDto organizationSignUpDto = new OrganizationSignUpDto(companyName);
        repository.insertOrganization(organizationSignUpDto);
        EmployeeSignUpDto employeeSignUpDto = new EmployeeSignUpDto(userSignUpDto.getNo(), companySignUpDto.getNo(), 0, organizationSignUpDto.getNo());
        repository.insertEmployee(employeeSignUpDto);
        int employeeSignUpDtoNo = employeeSignUpDto.getNo();
        List<Integer> serviceNo = repository.selectServiceNoByPackageNo(packageNo);
        for (int a:
        serviceNo) {
            System.out.println("a = " + a);
        }
        List<Integer> serviceNoByServiceFree = repository.selectServiceNoByServiceFree();
        for (int a:
                serviceNoByServiceFree) {
            System.out.println("a = " + a);
        }
        serviceNo.addAll(serviceNoByServiceFree);
        for (int num:
        serviceNo) {
            System.out.println("num = " + num);
            repository.insertServicePublished(employeeSignUpDtoNo, num);
        }

    }

    public String findCheckCompanyName(String companyName) {
        System.out.println("servicecompanyName = " + companyName);
        return repository.selectCompanyCheck(companyName);
    }

    public ShortLinkSignUpDto findRedirectLink(int empNo){
        System.out.println("serviceEmpNo = " + empNo);
        return repository.selectShortLinkByEmpNo(empNo);
    }

    public Integer findEmployeeStateCheck(int empNo){
        System.out.println("serviceEmpNo = " + empNo);
        return repository.selectEmployeeStateCheck(empNo);
    }

    @Transactional
    public void modifySignUpInvite(SignUpInviteUpdateDto dto){
        System.out.println("dto = " + dto);
        int empNo = dto.getEmpNo();
        Integer integerUserNo = repository.selectUserNoByEmployeeNo(dto);
        int userNo = integerUserNo.intValue();
        dto.setUserNo(userNo);
        repository.updateEmployeeStateTo2(dto);
        dto.setUserPw(passwordEncoder.encode(dto.getUserPw()));
        repository.updateInvitedUser(dto);
        List<Integer> serviceNoByServiceFree = repository.selectServiceNoByServiceFree();
        for (int num:
                serviceNoByServiceFree) {
            System.out.println("num = " + num);
            repository.insertServicePublished(empNo, num);
        }
    }

    public List<CompanyServiceListDto> findCompanyServiceList(int comNo){
        System.out.println("comNo = " + comNo);
        return repository.selectCompanyServiceList(comNo);
    }

    public Integer findPackageCountByCompanyNo(int comNo){
        System.out.println("comNo = " + comNo);
        return repository.selectPackageCountByCompanyNo(comNo);
    }

    public List<CountPublishedServiceAndEmpNoDto> findEachCompanyPublishedCount(List<CompanyServiceListDto> companyServiceListDto, int comNo) {
        List<CountPublishedServiceAndEmpNoDto> listDto = new ArrayList<CountPublishedServiceAndEmpNoDto>();
        for (CompanyServiceListDto dto : companyServiceListDto) {
            CountPublishedServiceAndEmpNoDto countPublishedServiceAndEmpNoDto = repository.selectEachCompanyPublishedCount(dto.getServiceNo(), comNo);
            countPublishedServiceAndEmpNoDto.setEmpNo(dto.getServiceNo());
            System.out.println("countPublishedServiceAndEmpNoDto = " + countPublishedServiceAndEmpNoDto);
            listDto.add(countPublishedServiceAndEmpNoDto);
        }

        return listDto;
    }

    public List<UnPublishedUserDto> findUnPublishedUser(int comNo, int serviceNo){
        System.out.println("comNo = " + comNo + ", serviceNo = " + serviceNo);
        return repository.selectUnPublishedUser(comNo, serviceNo);
    }

    @Loggable
    public void addInvitedEmployeePublish(int empNo, int serviceNo){
        System.out.println("empNo = " + empNo + ", serviceNo = " + serviceNo);
        repository.insertServicePublished(empNo,serviceNo);
    }

    public List<UnPublishedUserDto> findPublishedUser(int comNo, int serviceNo){
        System.out.println("comNo = " + comNo + ", serviceNo = " + serviceNo);
        return repository.selectPublishedUser(comNo, serviceNo);
    }

    @Loggable
    public void modifyUnPublish(int empNo, int serviceNo){
        System.out.println("empNo = " + empNo + ", serviceNo = " + serviceNo);
        repository.updateUnPublish(empNo,serviceNo);
    }
    public Integer findPublishedCount(int serviceNo, int comNo){
        System.out.println("serviceNo = " + serviceNo + ", comNo = " + comNo);
        return repository.selectPublishedCount(serviceNo,comNo);
    }


}

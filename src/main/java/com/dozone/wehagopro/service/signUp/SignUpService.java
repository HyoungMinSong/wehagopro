package com.dozone.wehagopro.service.signUp;

import com.dozone.wehagopro.domain.signUp.*;
import com.dozone.wehagopro.repository.signUp.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SignUpService {

    @Autowired
    SignUpRepository repository;

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
        String password = dto.getPassword();
        String email = dto.getEmail();
        String companyName = dto.getCompanyName();
        String businessRegistrationNumber = dto.getBusinessRegistrationNumber();
        String businessType = dto.getBusinessType();
        String businessStatus = dto.getBusinessStatus();
        String businessCategory = dto.getBusinessCategory();
        String representativeName = dto.getRepresentativeName();
        String companyPhoneNumber = dto.getCompanyPhoneNumber();
        int packageNo = dto.getPackageNo();
        UserSignUpDto userSignUpDto = new UserSignUpDto(id, password, name, phoneNumber, email);
        repository.signUpUser(userSignUpDto);
        CompanySignUpDto companySignUpDto = new CompanySignUpDto(companyName, packageNo, businessRegistrationNumber, businessType, businessStatus, businessCategory, representativeName, companyPhoneNumber);
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
}

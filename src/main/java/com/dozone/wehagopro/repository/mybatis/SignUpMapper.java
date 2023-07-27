package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.signUp.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignUpMapper {

    String idCheck(String id);
    void userSave(UserSignUpDto dto);
    void companySave(CompanySignUpDto dto);
    void organizationSave(OrganizationSignUpDto dto);
    void employeeSave(EmployeeSignUpDto dto);
    List<Integer> findServiceNoByPackageNo(int no);
    List<Integer> findServiceNoByServiceFree();
    void servicePublishedSave(int param1, int param2);
    String companyCheck(String companyName);
    ShortLinkSignUpDto findShortLinkByEmpNo(int empNo);
}

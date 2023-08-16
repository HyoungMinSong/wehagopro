package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.signUp.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignUpMapper {

    String idCheck(String id);
    String emailCheck(String email);
    String phoneNumberCheck(String phoneNumber);
    void userSave(UserSignUpDto dto);
    void companySave(CompanySignUpDto dto);
    void organizationSave(OrganizationSignUpDto dto);
    void employeeSave(EmployeeSignUpDto dto);
    List<Integer> findServiceNoByPackageNo(int no);
    List<Integer> findServiceNoByServiceFree();
    void servicePublishedSave(int param1, int param2);
    String companyCheck(String companyName);
    ShortLinkSignUpDto findShortLinkByEmpNo(int empNo);
    Integer employeeStateCheck(int empNo);
    Integer findUserNoByEmployeeNo(SignUpInviteUpdateDto dto);
    void updateEmployeeStateTo2(SignUpInviteUpdateDto dto);
    void updateInvitedUser(SignUpInviteUpdateDto dto);
    List<CompanyServiceListDto> companyServiceList(int comNo);
    Integer findPackageCountByCompanyNo(int comNo);
    CountPublishedServiceAndEmpNoDto eachCompanyPublishedCount(int param1, int param2);
    List<UnPublishedUserDto> findUnPublishedUser(int param1, int param2);
    List<UnPublishedUserDto> findPublishedUser(int param1, int param2);
    void updateUnPublish(int param1, int param2);
    Integer findPublishedCount (int param1, int param2);
}

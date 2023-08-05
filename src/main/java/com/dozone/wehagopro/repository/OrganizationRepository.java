package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.repository.mybatis.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationRepository {

    private final OrganizationMapper organizationMapper;

    // 직원 목록 유저,직원,부서이름 from 회사번호
    public List<OrganizationUserEmplDto> findUserEmplOrgaFromCompany(Integer t_company_no){
        return organizationMapper.findUserEmplOrgaFromCompany(t_company_no);
    };
    // 부서 목록 from 회사번호
    public List<OrganizationDto> findOrganizationFromCompany(Integer t_company_no){
        return organizationMapper.findOrganizationFromCompany(t_company_no);
    };
    // 조직도 부서 생성
    public void createOrganization(String t_organization_name, Integer t_organization_parent){
        organizationMapper.createOrganization(t_organization_name,t_organization_parent);
    };
    // 조직도 부서 수정
    public void updateOrganization(String t_organization_name, Integer t_organization_no){
        organizationMapper.updateOrganization(t_organization_name, t_organization_no);
    };
    // 조직도 부서 삭제
    public void deleteOrganization(Integer t_organization_no){
        organizationMapper.deleteOrganization(t_organization_no);
    };
    // 임시 회원 등록
    public Integer registerUser(OrganizationEmplRegiDTO dto){
        return organizationMapper.registerUser(dto);
    }
    // 회사 이름인 부서 번호 찾기
    public Integer findCompanyOrgaNoFromName(String t_organization_name){
        return organizationMapper.findCompanyOrgaNoFromName(t_organization_name);
    };
    // 직원 등록
    public Integer registerEmployee(OrganizationEmplRegiDTO dto){
        return organizationMapper.registerEmployee(dto);
    };
    // 메일 등록
    public void createShortLink(OrganizationEmplRegiDTO dto){
        organizationMapper.createShortLink(dto);
    }
    // 회사 이름 조회
    public String findCompanyNameFromCompanyNo(Integer t_company_no){
        return organizationMapper.findCompanyNameFromCompanyNo(t_company_no);
    };
    // 메일 받은 직원 상태 갱신
    public void updateReceivedMailEmployee(Integer t_employee_no){
        organizationMapper.updateReceivedMailEmployee(t_employee_no);
    }
    // 메일 기한 연장
    public void updateReceivedMailShortlink(Integer t_employee_no){
        organizationMapper.updateReceivedMailShortlink(t_employee_no);
    }
    // 유저 사진 수정
    public void updateDetailUser(OrganizationEmplRegiDTO dto){
        organizationMapper.updateDetailUser(dto);
    };
    // 직원 수정
    public void updateDetailEmployee(OrganizationEmplRegiDTO dto){
        organizationMapper.updateDetailEmployee(dto);
    };


}

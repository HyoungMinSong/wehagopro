package com.dozone.wehagopro.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    
    // 조직도 회사 이름
    String showMyCompany(Integer t_user_no);
    // 조직도 부서 목록
    List<String> showMyOrganization(Integer t_user_no);

}

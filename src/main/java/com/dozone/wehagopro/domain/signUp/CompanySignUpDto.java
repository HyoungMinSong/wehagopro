package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class CompanySignUpDto {
    private int no;
    private String companyName;
    private int packageNo;
    private String businessRegistrationNumber;
    private String businessType;
    private String businessStatus;
    private String businessCategory;
    private String representativeName;
    private String companyPhoneNumber;
    private int packageCount;

    public CompanySignUpDto(String companyName, int packageNo, String businessRegistrationNumber, String businessType, String businessStatus, String businessCategory, String representativeName, String companyPhoneNumber, int packageCount) {
        this.companyName = companyName;
        this.packageNo = packageNo;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessType = businessType;
        this.businessStatus = businessStatus;
        this.businessCategory = businessCategory;
        this.representativeName = representativeName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.packageCount = packageCount;
    }
}

package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class SaveArrayInvitedEmployeePublishRequestDto {

    private int serviceNo;
    private int[] arrayEmployeeNo;
    private int comNo;
    private int packCt;
    private int totalAddEmployeeCount;
}
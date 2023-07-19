package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class CountEmployee {

    private Integer count_state_all;

    private Integer count_state_0;

    private Integer count_state_1;

    private Integer count_state_2;

    private Integer count_state_3;

    public CountEmployee() {
    }

    public CountEmployee(Integer count_state_all, Integer count_state_0, Integer count_state_1, Integer count_state_2, Integer count_state_3) {
        this.count_state_all = count_state_all;
        this.count_state_0 = count_state_0;
        this.count_state_1 = count_state_1;
        this.count_state_2 = count_state_2;
        this.count_state_3 = count_state_3;
    }
}

package com.dozone.wehagopro.domain;

import lombok.Data;
@Data
public class Item2 {
    private int num;
    private String id;
    private String pw;

    public Item2() {

    }


    public Item2(String name, String idd, String pw) {


        this.id = id;
        this.pw = pw;
    }

    public Item2(String id, String pw) {

        this.id = id;
        this.pw = pw;
    }

}
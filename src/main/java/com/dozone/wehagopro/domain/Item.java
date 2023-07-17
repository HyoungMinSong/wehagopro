package com.dozone.wehagopro.domain;

import lombok.Data;
@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String idd;
    public Item() {
    }
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
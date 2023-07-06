package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.Item;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    MyBatisItemRepository repository;

    @GetMapping("/hello")
    public void test(){
        Item item = new Item("테스트", 300, 200);
        repository.save(item);

    }
}

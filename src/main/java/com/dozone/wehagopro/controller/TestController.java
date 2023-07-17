package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.Item;
import com.dozone.wehagopro.repository.ItemSearchCond;
import com.dozone.wehagopro.repository.ItemUpdateDto;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    MyBatisItemRepository repository;

    @ResponseBody
    @PostMapping("/hello")
    public int test(){
        Item item = new Item("테스트", 300, 200);
        repository.save(item);
        return 1;

    }

    @ResponseBody
    @GetMapping("/select")
    public Item select(){
        Item byId = repository.findById(1L);
        System.out.println(byId);
        return byId;
    }
    @ResponseBody
    @GetMapping("/selectall")
    public List<Item> selectAll(){

        // 세션 or 토큰 유효성 검사


        ItemSearchCond itemSearchCond = new ItemSearchCond("테스트", 500);
        return repository.findAll(itemSearchCond);

    }

    @PatchMapping("/update")
    public void update(){
        ItemUpdateDto itemUpdateDto = new ItemUpdateDto("수정", 700, 800);
        repository.update(10L,itemUpdateDto);
        System.out.println("업데이트 끝");
    }

    @DeleteMapping("/delete")
    public void delete(){
        repository.delete(10L);
        System.out.println("딜리트 끝");
    }
}

package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.Item;
import com.dozone.wehagopro.domain.Item2;
import com.dozone.wehagopro.domain.LoginForm;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TestController {
    @Autowired
    MyBatisItemRepository repository;



    @GetMapping("/hello")
    public void test() {
        Item item = new Item("테스트", 300, 200);
        repository.save(item);

    }

//    @GetMapping ("/login")
//    public Item2 test6() {
//        Item2 mmm = new Item2();
//        repository.find(mmm.getName());
//        System.out.println("조회한 내용"+ mmm.getName());
//        //로그 찍어보는건 return반환 전에 해야함
//        return mmm;
//
//    }

//    @PostMapping("/login")
//    public Item2 test6(@RequestParam("username") String username, @RequestParam("password") String password) {
//
//        Item2 mmm = new Item2();
//        repository.find(mmm.getName());
//        System.out.println("조회한 내용"+ mmm.getName());
//        //로그 찍어보는건 return반환 전에 해야함
//        return mmm;
//


        // 입력 받은 아이디(username)와 비밀번호(password)를 사용하여 DB에서 조회 수행
        // 조회한 결과를 이용하여 로그인 처리를 진행하거나 다른 작업을 수행합니다.
        // ...

    @PostMapping("/login")
    public Item2 test7(@RequestBody LoginForm loginForm) {
        System.out.println("하이");
        String userid = loginForm.getUserid();
        System.out.println("userid = " + userid);
        String password = loginForm.getPassword();
        System.out.println("password = " + password);

//                String userid = loginForm.get("userid");
//        System.out.println("userid = " + userid);
//        String password = loginForm.get("password");
//        System.out.println("password = " + password);


        Item2 qqq = repository.findidpw(userid, password);
        System.out.println(qqq.getT_user_id() + qqq.getT_user_password());
        return qqq;
    }

//    @PostMapping("/login")
//    public Item2 test7(@RequestParam("username") String username, @RequestParam("password") String password) {
//        Item2 qqq = repository.findidpw(username, password);
//        System.out.println(qqq.getId() + qqq.getPw());
//        return qqq;
//    }
}
//
//    @GetMapping("/hi")
//    public Item2 test2(String idd) {
//        //@RequestParam은 스프링에서 해줘서 생략가능
//        return repository.find(idd);
//        //repository의 find매소드의 매개변수 idd에 값이 담긴다
//    }
//
//    @PostMapping("/hi2")
//    public void test4() {
//        Item2 item2 = new Item2("jong1", "jongwon", "jong2");
//        //인스턴스 생성 후 item2 class에 생성자를 지정해 준다
//        repository.update1(item2);
//
//    }
//
//    @PostMapping("/hi1")
//    public void test3(String bbb) {
//        repository.save1(bbb);
//    }
//
//
//
//    @GetMapping("/hi3")
//    public void test5() {
//        Item2 item2 = new Item2("jong1", "jong11", "jong111");
//        System.out.println("쿼리문실행전"+item2.getName());
//        repository.delete(item2.getName());
//
//    }
//
//
//    @GetMapping("/hii")
//    //Get방식으로 웹에서(/hii) 요청이 들어오면 @GetMapping된 어노테이션들 중에서 (/hii) 적혀있는 곳에서 요청을 받는다
//    public Item2 test7() {
//        //Item2는 반환자료형으로 객체형식의 자료형이다. 리턴값과 자료형을 통일 해 줘야한다 다르면 에러뜸
//        Item2 item2 = new Item2();
//        item2.setPw("123");
//        //값을 넣기위해 set으로 "123"을 넣었다
//        return item2;
//        //리턴자료형을 객체로 했기때문에 객체로 반환해야한다. 객체형식으로 반환하면 JSON 형식으로 보낸다
//        //EX) name: 홍길동, age: 39 // json 데이터를 표현하는 데 사용되는 형식
//        //return json 형식으로 반환 무조건 해주는게 아님
//        //@controller 대신 @restcontroller 로 해야 json 형식으로 반환해줌
//    }
//
//    @GetMapping("/hiiii")
//    public String test8() {
//        //위의 것과 차이점은 반환자료형이 객체가 아닌 String 문자형이다
//        return "안녕";
//        //String이 반환자료형이어서 "안녕" 문자로 반환한다
//
//    }
//}

//@RestController
//public class TestController2{
//
//    @Autowired
//    MyBatisItemRepository exam;
//
//    @GetMapping("/ex1")
//    public Item2 search(Item2 xxx){
//    return reposi
//    }
//
//
//
//        }



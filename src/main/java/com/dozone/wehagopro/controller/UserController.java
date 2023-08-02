package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.config.JwtTokenProvider;
import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.service.OrganizationService;
import com.dozone.wehagopro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;


//    @PostMapping("/signup")
//    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequestDto request) {
//        try {
//            UserDto userDto = userService.signUp(request);
//            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserLoginRequestDto request) {
        TokenDto tokens = userService.login(request.getUserid(), request.getPassword());
        return tokens;
    }

    @GetMapping("/data")
    public UserInfoDto loginUserData(@RequestHeader("Authorization") String accessToken) {
        UserInfoDto userInfoDto = userService.getUserInfo(accessToken);
        return userInfoDto;
    }

//    @PostMapping("/reissue")
//    public TokenDto reissue(@RequestHeader("Authorization") String refreshToken) {
//        TokenDto tokens = userService.reissueAccessToken(refreshToken);
//        return tokens;
//    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody TokenDto request) {
        if(userService.logout(request)) {
            return new ResponseEntity<>("로그아웃 되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("로그아웃 실패..", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/change_image")
    public PhotoDto updateUserInfo(@RequestParam("profileImage") MultipartFile file,
                                   @RequestParam("name") String name,
                                   @RequestParam("id") String id,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone) {
        return userService.updateUserInfo(file, name, id, email, phone);
    }

    @PostMapping("/update/keep_image")
    public ResponseEntity<?> updateUserInfoKeepImage(@RequestParam(value = "file", required = false) String file,
                                        @RequestParam("name") String name,
                                        @RequestParam("id") String id,
                                        @RequestParam("email") String email,
                                        @RequestParam("phone") String phone) {
        try {
            System.out.println("name : " + name + "id: " + id + "email: " + email + "phone: " + phone);
            userService.updateUserInfoKeepImage(name, id, email, phone);
            return ResponseEntity.ok("유저 정보 update 성공!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("유저 정보 update 실패..");
        }
    }
}
package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.config.JwtTokenProvider;
import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/reissue")
    public TokenDto reissue(@RequestHeader("Authorization") String refreshToken) {
        TokenDto tokens = userService.reissueAccessToken(refreshToken);
        return tokens;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody TokenDto request) {
        if(userService.logout(request)) {
            return new ResponseEntity<>("로그아웃 되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("로그아웃 실패..", HttpStatus.BAD_REQUEST);
        }
    }
}
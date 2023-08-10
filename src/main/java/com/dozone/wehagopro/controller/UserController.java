package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.config.JwtTokenProvider;
import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.service.OrganizationService;
import com.dozone.wehagopro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/update/userInfo")
    public PhotoDto updateUserInfo(@RequestHeader("Authorization") String accessToken,
                                   @RequestParam(value = "profileImage", required = false) MultipartFile file,
                                   @RequestParam("isDelete") boolean isDelete,
                                   @RequestParam("name") String name,
                                   @RequestParam("id") String id,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone) {
        return userService.updateUserInfo(accessToken, file, isDelete, name, id, email, phone);
    }

    @PostMapping("/update/userPassword")
    public boolean updateUserPassword(@RequestHeader("Authorization") String accessToken,
                                      @RequestParam("id") String id,
                                      @RequestParam("currentPassword") String currentPassword,
                                      @RequestParam("newPassword") String newPassword) {
        return userService.updateUserPassword(accessToken, id, currentPassword, newPassword);
    }
}
package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        TokenDto token = userService.findLogin(request.getUserid(), request.getPassword());
        return token;
    }

    @GetMapping("/data")
    public UserInfoDto loginUserData(final Authentication authentication) {
        UserInfoDto userInfoDto = userService.findUserInfo(authentication);
        return userInfoDto;
    }

//    @PostMapping("/reissue")
//    public TokenDto reissue(@RequestHeader("Authorization") String refreshToken) {
//        TokenDto tokens = userService.reissueAccessToken(refreshToken);
//        return tokens;
//    }

    @PostMapping("/update/userInfo")
    public PhotoDto updateUserInfo(final Authentication authentication,
                                   @RequestParam(value = "profileImage", required = false) MultipartFile file,
                                   @RequestParam("isDelete") boolean isDelete,
                                   @RequestParam("name") String name,
                                   @RequestParam("id") String id,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone) {
        if (authentication == null) {
            throw new IllegalArgumentException("not valid accessToken!");
        }
        return userService.modifyUserInfo(file, isDelete, name, id, email, phone);
    }

    @PostMapping("/update/userPassword")
    public boolean updateUserPassword(final Authentication authentication,
                                      @RequestParam("id") String id,
                                      @RequestParam("currentPassword") String currentPassword,
                                      @RequestParam("newPassword") String newPassword) {
        if (authentication == null) {
            throw new IllegalArgumentException("not valid accessToken!");
        }
        return userService.modifyUserPassword(id, currentPassword, newPassword);
    }

    @GetMapping("/select/notice/limit5")
    public List<NoticeSelectDto> selectNoticeLimit5(@RequestParam("companyNo") int companyNo) {
        return userService.findNoticeLimit5(companyNo);
    }
}
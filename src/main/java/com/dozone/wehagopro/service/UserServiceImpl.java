package com.dozone.wehagopro.service;

import com.dozone.wehagopro.config.JwtTokenProvider;
import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.repository.mybatis.UserMapper;
import com.dozone.wehagopro.repository.mybatis.BlackListMapper;
import com.dozone.wehagopro.repository.mybatis.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
//    private final RedisDao redisDao;
//    private final RefreshTokenMapper refreshTokenMapper;
//    private final BlackListMapper blackListMapper;

//    @Transactional
//    @Override
//    public UserDto signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception {
//        Optional<UserDto> userDto = userMapper.findByUserId(userSignUpRequestDto.getId());
//        if (userDto.isPresent()) {
//            throw new Exception("이미 존재하는 회원 입니다.");
//        }
//
//        if (!requestDto.getPassword().equals(requestDto.getPassword())) {
//            throw new Exception("비밀번호가 일치하지 않습니다.");
//        } // 나중에 checkedPassword로 수정
//
//        UserDto userDto = requestDto.toEntity();
//        userDto.encodePassword(passwordEncoder);
//        userMapper.insertMember(userDto.getId(), userDto.getPassword(), userDto.getName(), userDto.getRole().toString(), userDto.getEnabled());
//
//        return userDto;
//    }

    @Transactional
    @Override
    public TokenDto login(String id, String password) {
        UserDto userDto = userMapper.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));

        // passwordEncoder.matches(로그인 할 때 비밀번호, DB에 저장된 비밀번호)
//        if (!passwordEncoder.matches(password, userDto.getT_user_password())) {
//            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
//        }

        TokenDto tokenDto = new TokenDto(
                jwtTokenProvider.createAccessToken(userDto),
                jwtTokenProvider.createRefreshToken(userDto)
        );

        return tokenDto;
    }

    @Transactional
    @Override
    public boolean logout(TokenDto tokenDto) {
        // Access Token 검증
        if (!jwtTokenProvider.validateToken(tokenDto.getAccessToken())) {
            new RuntimeException("유효하지 않은 Access Token 입니다.");
            return false;
        } else {
            // Access Token 에서 authentication 가져오기
            Authentication authentication = jwtTokenProvider.getAuthentication(tokenDto.getAccessToken());

//          // Redis에 저장된 Refresh Token 제거
//          redisDao.deleteValues(authentication.getName());

//            // DB에 저장된 Refresh Token 제거
//            refreshTokenMapper.deleteToken(authentication.getName());

            // Access Token 블랙 리스트에 등록하여 만료 시키기
            long expiration = jwtTokenProvider.getExpiration(tokenDto.getAccessToken());

//          redisDao.setBlackList(accessToken, "access_token", expiration);
//            blackListMapper.insertBlackList(tokenDto.getAccessToken(), expiration);

            return true;
        }
    }

    @Transactional
    @Override
    public TokenDto reissueAccessToken(String refreshToken) {
            // Refresh Token 에서 Username (pk) 가져오기
            Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken.substring(7));

            // userId로 유저 검색
        UserDto userDto = userMapper.findByUserId(authentication.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자가 없습니다."));
//                String refreshToken = redisDao.getValues(member.getId());
//            String refreshToken = refreshTokenMapper.getToken(userDto.getT_user_id());

            // 리프레시 토큰 불일치 에러
//            if (!refreshToken.equals(tokenDto.getRefreshToken())) {
//                throw new IllegalArgumentException("Refresh Token이 불일치 합니다. 다시 로그인 하세요.");
//            }

            // Access Token, Refresh Token 재발급
            // 단순히 Access Token만 재발급 받는 것보다는 더 좋은 방법이라 함
            String newAccessToken = jwtTokenProvider.createAccessToken(userDto);
            String newRefreshToken = jwtTokenProvider.createRefreshToken(userDto);

            // Redis에 Refresh Token 업데이트
//                long expiration = jwtTokenProvider.getExpiration(newRefreshToken);
//                redisDao.setValues(member.getId(), newRefreshToken, Duration.ofMillis(expiration));
//                refreshTokenMapper.updateToken(userDto.getT_user_id(), newRefreshToken, expiration);

            // TokenDto에 담아서 리턴
            TokenDto newTokens = new TokenDto(newAccessToken, newRefreshToken);
            return newTokens;
    }

    @Override
    public UserInfoDto getUserInfo(String accessToken) {
        String userId = jwtTokenProvider.getUserId(accessToken.substring(7));
        UserDto userDto = userMapper.findByUserId(userId).get();
        List<UserCompanyDto> userCompanyDtoList = userMapper.getUserCompanyList(userId);
        List<UserServiceDto> userServiceDtoList = userMapper.getUserServiceList(userId);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setT_user_name(userDto.getT_user_name());
        userInfoDto.setT_user_email(userDto.getT_user_email());
        userInfoDto.setT_user_photo_path(userDto.getT_user_photo_path());
        userInfoDto.setUserCompanyDtoList(userCompanyDtoList);
        userInfoDto.setUserServiceDtoList(userServiceDtoList);

        return userInfoDto;
    }
}
package com.dozone.wehagopro.service;

import com.dozone.wehagopro.config.JwtTokenProvider;
import com.dozone.wehagopro.config.RedisDao;
import com.dozone.wehagopro.domain.Member;
import com.dozone.wehagopro.domain.MemberSignUpRequestDto;
import com.dozone.wehagopro.domain.TokenDto;
import com.dozone.wehagopro.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisDao redisDao;

    @Transactional
    @Override
    public String signUp(MemberSignUpRequestDto requestDto) throws Exception {

        if (memberMapper.findByMembername(requestDto.getId()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (!requestDto.getPassword().equals(requestDto.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        } // 나중에 checkedPassword로 수정

        Member member = requestDto.toEntity();
        member.encodePassword(passwordEncoder);
        memberMapper.insertMember(member.getId(), member.getPassword(), member.getName(), member.getRole().toString(), member.getEnabled());

        return member.getId();
    }

    @Transactional
    @Override
    public TokenDto login(Map<String, String> members) {

        Member member = memberMapper.findByMembername(members.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));

        String password = members.get("password");

        // passwordEncoder.matches(로그인 할 때 비밀번호, DB에 저장된 비밀번호)
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String role = member.getRole();

        TokenDto tokenDto = new TokenDto(
                jwtTokenProvider.createAccessToken(member.getId(), role),
                jwtTokenProvider.createRefreshToken(member.getId(), role)
        );

        return tokenDto;
    }

    @Transactional
    @Override
    public String logout(String accessToken, String refreshToken) {
        // Access Token 검증
        if (!jwtTokenProvider.validateToken(accessToken)) {
            return "유효하지 않은 Access Token 입니다.";
        }

        // Access Token 에서 authentication 가져오기
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        // Redis에 저장된 Refresh Token 제거
        redisDao.deleteValues(authentication.getName());

        // Access Token 블랙 리스트에 등록하여 만료 시키기
        long expiration = jwtTokenProvider.getExpiration(accessToken);
        redisDao.setBlackList(accessToken, "access_token", expiration);

        return "로그아웃 성공!";
    }

    // Access Token 재발급
    @Transactional
    @Override
    public TokenDto reissueAccessToken(TokenDto tokenDto) {
        // Access Token 만료되었는지 확인
        if(!jwtTokenProvider.validateToken(tokenDto.getAccessToken())) {
            // Refresh Token이 유효하다면
            if (jwtTokenProvider.validateToken(tokenDto.getRefreshToken())) {
                // Refresh Token 에서 Username (pk) 가져오기
                Authentication authentication = jwtTokenProvider.getAuthentication(tokenDto.getRefreshToken());

                // user pk로 유저 검색 / repo 에 저장된 Refresh Token 이 없음
                Member member = memberMapper.findByMembername(authentication.getName())
                        .orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자가 없습니다."));
                String refreshToken = redisDao.getValues(member.getId());

                // 리프레시 토큰 불일치 에러
                if (!refreshToken.equals(tokenDto.getRefreshToken())) {
                    throw new IllegalArgumentException("Refresh Token이 불일치 합니다. 다시 로그인 하세요.");
                }

                // Access Token, Refresh Token 재발급
                // 단순히 Access Token만 재발급 받는 것보다는 더 좋은 방법이라 함
                String newAccessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole());
                String newRefreshToken = jwtTokenProvider.createRefreshToken(member.getId(), member.getRole());

                // Redis에 Refresh Token 업데이트
                long expiration = jwtTokenProvider.getExpiration(newRefreshToken);
                redisDao.setValues(member.getId(), newRefreshToken, Duration.ofMillis(expiration));

                // TokenDto에 담아서 리턴
                TokenDto newTokens = new TokenDto(newAccessToken, newRefreshToken);
                return newTokens;

            } else {
                // 만료된 refresh token 에러
                throw new IllegalArgumentException("만료된 Refresh Token 입니다. 다시 로그인 하세요.");
            }
        } else {
            throw new IllegalArgumentException("Access Token이 아직 유효합니다. Access Token 재발급 실패..");
        }
    }
}
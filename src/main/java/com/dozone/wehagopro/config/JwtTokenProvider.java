package com.dozone.wehagopro.config;

import com.dozone.wehagopro.domain.UserDto;
import com.dozone.wehagopro.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final CustomUserDetailsService customUserDetailsService;
    @Value("${jwt.secret}")
    private String secretKey;

    // 토큰 유효시간
    private long accessTokenValidTime = 1000L * 60 * 10; // 10분
    private long refreshTokenValidTime = 1000L * 60 * 30; // 30분

    // 객체 초기화, secretKey 를 Base64로 인코딩합니다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT Access 토큰 생성
    public String createAccessToken(UserDto userDto, Map<String, String> userRole) {
        Claims claims = Jwts.claims().setSubject(userDto.getT_user_id()); // JWT payload 에 저장되는 정보단위
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .claim("no", userDto.getT_user_no())
                .claim("role", userRole) // 권한 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘
                // signature 에 들어갈 secret 값 세팅
                .compact();
    }

    // JWT Refresh 토큰 생성
    public String createRefreshToken(UserDto userDto, Map<String, String> userRole){
        Claims claims = Jwts.claims().setSubject(userDto.getT_user_id());
        Date now = new Date();
        long expiration = now.getTime() + refreshTokenValidTime;
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .claim("no", userDto.getT_user_no())
                .claim("role", userRole)
                .setIssuedAt(now)
                .setExpiration(new Date(expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

//        // redis에 저장
//        redisDao.setValues(userId, refreshToken, Duration.ofMillis(refreshTokenValidTime));

        // DB에 저장
//        if(refreshTokenMapper.getToken(userDto.getT_user_id()) == null) {
//            refreshTokenMapper.insertToken(userDto.getT_user_id(), refreshToken, expiration);
//        }

        return refreshToken;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰에서 만료시간 추출
    public Long getExpiration(String token) {
        // accessToken 남은 유효시간
        Date expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
        return expiration.getTime();
    }

    // Request의 Header에서 token 값을 가져옴
    public String resolveToken(HttpServletRequest request) {
        if(request.getHeader(HttpHeaders.AUTHORIZATION) != null ) {
            return request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        }
        return null;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            // Redis로 로그아웃 여부 확인
//            if(redisDao.hasKeyBlackList(token)) {
//                return false;
//            }
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

//    public boolean isLogout(String accessToken) {
//        if(blackListMapper.hasBlackList(accessToken) == null) {
//            return false;
//        }
//        return accessToken.equals(blackListMapper.hasBlackList(accessToken)) ? true : false;
//    }
}

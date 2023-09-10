package com.mmt.diagnosis.service.authority;

import com.mmt.diagnosis.exception.UnauthorizedException;
import com.mmt.diagnosis.jwt.JwtToken;
import com.mmt.diagnosis.jwt.TokenProvider;
import com.mmt.diagnosis.util.RedisUtil;
import com.mmt.diagnosis.util.SecurityUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthService(TokenProvider tokenProvider, RedisUtil redisUtil, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.redisUtil = redisUtil;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /**
     * authorize : 로그인 요청이 들어오면 유저 정보로 인증 과정을 진행
     * Authentication Token 객체를 생성해 검증 과정을 진행. 그 검증된 인증 정보로 JWT 토큰을 생성
     */
    public JwtToken authorize(String email, String password) {
        // Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 검증된 인증 정보로 JWT 토큰 생성
        JwtToken token = tokenProvider.generateToken(authentication);
        return token;
    }

    /**
     * reissue : 토큰 재발급
     */
    @Transactional
    public JwtToken reissue(String accessToken, String refreshToken) {
        // 바디로 들어온 refreshToken 한 번 검증
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new UnauthorizedException("유효하지 않은 RefreshToken 입니다");
        }
        // accessToken에서 email 가져오기
        String userEmail = tokenProvider.getEmail(accessToken);

        // refreshToken 검증 후 새 토큰 발급
        if (redisUtil.hasKey(userEmail) && refreshToken.equals(redisUtil.get(userEmail))){
            // 기존 accessToken 블랙리스트에 넣고 (refreshToken 토큰의 기간만큼)
            redisUtil.setBlackList(accessToken, "logout", tokenProvider.getExpiration(refreshToken));
            // 새 토큰 발급
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            JwtToken newToken = tokenProvider.generateToken(authentication);
            String newRefreshToken = newToken.getRefreshToken();
            // 새 토큰 RedisDB에 저장
            redisUtil.set(authentication.getName(), newRefreshToken, tokenProvider.getExpiration(newRefreshToken));

            return newToken;
        } else throw new UnauthorizedException("만료됐거나 유효하지 않은 Token 입니다. 다시 로그인해주세요.");

    }


    /**
     * logout : accessToken - 블랙리스트에 올리기, refreshToken - RedisDB에서 삭제
     */
    @Transactional
    public void logout(String accessToken) {
        // email 가져오기
        String userEmail = SecurityUtil.getCurrentUserEmail().orElse("");
        // refreshToken 지우기
        redisUtil.delete(userEmail);
        // access토큰 blackList에 올리기
        redisUtil.setBlackList(accessToken, "logout", tokenProvider.getExpiration(accessToken));
    }

}

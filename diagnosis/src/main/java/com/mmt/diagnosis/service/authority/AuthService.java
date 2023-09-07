package com.mmt.diagnosis.service.authority;

import com.mmt.diagnosis.jwt.JwtToken;
import com.mmt.diagnosis.jwt.TokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthService(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /**
     * authorize : 로그인 요청이 들어오면 유저 정보로 인증 과정을 진행
     * Authentication Token 객체를 생성해 검증 과정을 진행. 그 검증된 인증 정보로 JWT 토큰을 생성
     */
    public JwtToken authorize(String email, String password) {
        System.out.println("password 잘 전달됐는지 : "+ password);
        // Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        System.out.println("authenticationToken : " + authenticationToken);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("authentication : "+ authentication);
        // 검증된 인증 정보로 JWT 토큰 생성
        JwtToken token = tokenProvider.generateToken(authentication);
        System.out.println("토큰이 잘 생성 됐는지 : "+ token.getAccessToken());
        return token;
    }

}

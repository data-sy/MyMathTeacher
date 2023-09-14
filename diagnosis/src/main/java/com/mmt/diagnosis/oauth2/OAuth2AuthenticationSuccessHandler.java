package com.mmt.diagnosis.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmt.diagnosis.jwt.JwtToken;
import com.mmt.diagnosis.jwt.TokenProvider;
import com.mmt.diagnosis.repository.cookie.CookieAuthorizationRequestRepository;
import com.mmt.diagnosis.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.mmt.diagnosis.repository.cookie.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


//    @Value("${oauth2.authorizedRedirectUri}")
//    private String redirectUri;
    private String redirectUri = "http://localhost:8080/login/oauth2/code/google";
//    private String redirectUri = "http://localhost:8080/login/oauth2/code/naver";
//    private String redirectUri = "http://localhost:8080/login/oauth2/code/kakao";

    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("OAuth2AuthenticationSuccessHandler.onAuthenticationSuccess() 실행 - 성공핸들러 - onAuthenticationSuccess 진입");
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed.");
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

//        // 헤더와 바디에 담아 보내기?
//        // 응답 바디에 담아서 보내기
//        JwtToken token = tokenProvider.generateToken(authentication);
//        response.setHeader("Authorization", "Bearer " + token);
//        // JWT 토큰을 응답 본문에 추가 (예시: JSON 형태로 추가)
//        ObjectMapper objectMapper = new ObjectMapper();
//        String tokenJson = objectMapper.writeValueAsString(token);
//        response.getWriter().write(tokenJson);
//        response.getWriter().flush();

    }


    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("OAuth2AuthenticationSuccessHandler.determineTargetUrl() 실행 - 성공핸들러 - determineTargetUrl 진입");
        Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new RuntimeException("redirect URIs are not matched.");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        //JWT 생성
        JwtToken token = tokenProvider.generateToken(authentication);
        System.out.println("핸들러에서 토큰 잘 생성 됐는지 : " + token.toString());

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create(redirectUri);

        if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
            return true;
        }
        return false;
    }
}
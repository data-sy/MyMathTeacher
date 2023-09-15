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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Optional;

import static com.mmt.diagnosis.repository.cookie.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
// 쿠키레파지토리는 securityConfig에서 warning 뜨고 실제로도 안 먹혀서 - 우선 패스
    // getDefaultTargetUrl를 통해 "/"가 리다이렉트 경로가 되는 중..

//    @Value("${oauth2.authorizedRedirectUri}")
//    private String redirectUri;
//    private String redirectUri = "http://localhost:8080/login/oauth2/code/google";
//    private String redirectUri = "http://localhost:8080/login/oauth2/code/naver";
//    private String redirectUri = "http://localhost:8080/login/oauth2/code/kakao";

    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        if (response.isCommitted()) {
            log.debug("Response has already been committed.");
            return;
        }

        // 리다이렉트
        String targetUrl = determineTargetUrl(request, response, authentication);
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }


    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        // 쿠키에 담는 거 성공하면 주석 없애고 사용
//        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new RuntimeException("redirect URIs are not matched.");
//        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        //JWT 생성
        JwtToken token = tokenProvider.generateToken(authentication);

//        // 토큰을 인코딩해서 보낸다면 사용
//        String encodedToken = null;
//        try {
//            encodedToken = URLEncoder.encode(String.valueOf(token), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace(); // 예외 처리 필요
//        }

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//        URI authorizedUri = URI.create(redirectUri);
//
//        if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
//            return true;
//        }
//        return false;
//    }

}
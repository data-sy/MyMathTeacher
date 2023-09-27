package com.mmt.diagnosis.config;

import com.mmt.diagnosis.jwt.JwtAccessDeniedHandler;
import com.mmt.diagnosis.jwt.JwtAuthenticationEntryPoint;
import com.mmt.diagnosis.jwt.JwtFilter;
import com.mmt.diagnosis.jwt.TokenProvider;
import com.mmt.diagnosis.oauth2.OAuth2AuthenticationFailureHandler;
import com.mmt.diagnosis.oauth2.OAuth2AuthenticationSuccessHandler;
import com.mmt.diagnosis.repository.cookie.CookieAuthorizationRequestRepository;
import com.mmt.diagnosis.service.users.CustomOAuth2UserService;
import com.mmt.diagnosis.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;



    public SecurityConfig(TokenProvider tokenProvider, RedisUtil redisUtil,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler,
                          OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler,
                          CustomOAuth2UserService customOAuth2UserService, CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository) {
        this.tokenProvider = tokenProvider;
        this.redisUtil = redisUtil;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
        this.customOAuth2UserService = customOAuth2UserService;
        this.cookieAuthorizationRequestRepository = cookieAuthorizationRequestRepository;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())       // rest api, token 사용하므로
                // Cors 설정
                .cors()
//                .configurationSource(corsConfigurationSource())
                .and()
                // 잘못된 접근에 대한 예외처리
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                // 사용하지 않는 것들 비활성화
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                // 요청들 접근 제한
                .authorizeRequests()
                // 프론트 개발을 위해 우선 열어둠
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/login.html", "/oauth2/**").permitAll()
                .antMatchers("/", "/favicon.ico", "/api/v1/hello/**", "/api/v1/signup", "/api/v1/authenticate", "/api/v1/reissue").permitAll()
                .antMatchers("/login.html", "/oauth2/**").permitAll()
                .anyRequest().authenticated();
        http
                // OAuth2 로그인
                .oauth2Login()
//                .authorizationEndpoint().authorizationRequestRepository(cookieAuthorizationRequestRepository)
//                .and()
//                .redirectionEndpoint().baseUri("/login/oauth2/code/**")
//                  .and()
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);
        // 필터 적용
        http
                .addFilterBefore(new JwtFilter(tokenProvider, redisUtil), UsernamePasswordAuthenticationFilter.class);
        // 로그아웃
        http
                .logout()
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.addAllowedOrigin("http://localhost:3000"); // 뷰
//        configuration.addAllowedOrigin("http://localhost:5000"); // 플라스크
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/api/v1/**", configuration);
//        return source;
//    }

}

package com.mmt.diagnosis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {}

    /**
     * Security Context의 Authenication객체를 이용해 userEmail을 리턴해주는 유틸성 메서드
     * (JwtFilter의 doFliter메서드에서 저장됨)
     */
    public static Optional<String> getCurrentUserEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String userEmail = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            userEmail = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            userEmail = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userEmail);
    }

}

package com.mmt.diagnosis.jwt;

import com.mmt.diagnosis.exception.UnauthorizedException;
import com.mmt.diagnosis.util.RedisUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final Key key;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final RedisUtil redisUtil;
    public TokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInMilliseconds,
            @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInMilliseconds,
            RedisUtil redisUtil) {
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds*1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds*1000;
        this.redisUtil = redisUtil;
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
    }

    // Authentication객체의 권한정보를 담아 jwt 토큰 반환
    public JwtToken generateToken(Authentication authentication) {
        long now = (new Date()).getTime();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now + accessTokenValidityInMilliseconds))
                .compact();

        //Refresh Token 생성
        String refreshToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now + refreshTokenValidityInMilliseconds))
                .compact();

        //Refresh Token RedisDB에 저장
        redisUtil.set(authentication.getName(), refreshToken, getExpiration(refreshToken));

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // jwt 토큰에 담겨있는 권한정보를 이용해서 Authentication객체 반환
    public Authentication getAuthentication(String accessToken) {
        //토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰의 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            if (redisUtil.hasKeyBlackList(token)) {
                throw new UnauthorizedException("로그아웃한 상태입니다.");
            }
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token. 잘못된 JWT 서명입니다.", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token. 만료된 JWT 토큰입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token. 지원되지 않는 JWT 토큰입니다.", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty. JWT 토큰이 잘못되었습니다.", e);
        } catch (UnauthorizedException e) {
            logger.info("로그아웃한 상태입니다.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // 토큰의 유효시간
    public Long getExpiration(String token) {
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration();
        Long now = new Date().getTime();
        return expiration.getTime() - now;
    }

    // 토큰에서 Email 추출
    public String getEmail(String token){
        Authentication authentication = getAuthentication(token);
        return authentication.getName();
    }

}

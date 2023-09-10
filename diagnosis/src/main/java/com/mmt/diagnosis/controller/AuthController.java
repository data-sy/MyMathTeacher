package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.users.LoginDTO;
import com.mmt.diagnosis.dto.users.TokenDTO;
import com.mmt.diagnosis.jwt.JwtFilter;
import com.mmt.diagnosis.jwt.JwtToken;
import com.mmt.diagnosis.service.authority.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authorize(@Valid @RequestBody LoginDTO loginDTO) {
        JwtToken token = authService.authorize(loginDTO.getUserEmail(), loginDTO.getUserPassword());

        // 토큰을 Response Header에도 넣어주자
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(TokenDTO.from(token), httpHeaders, HttpStatus.OK);
    }

    /**
     * AccessToken이 만료되었을 때 토큰(AccessToken , RefreshToken)재발급
     */
    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@Valid @RequestBody TokenDTO request) {
        JwtToken token = authService.reissue(request.getAccessToken(), request.getRefreshToken());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(TokenDTO.from(token), httpHeaders, HttpStatus.OK);

    }

    /**
     * 로그아웃 했을 때 토큰을 받아 BlackList에 저장
     * 리팩토링 : 프론트 단에서 accessToken의 시간 파싱할 수 있다면 바디에 그 시간만 담아서 보내기
     */
    @DeleteMapping("/authenticate")
    public ResponseEntity<Void> logout(@RequestBody @Valid TokenDTO request) {
        authService.logout(request.getAccessToken());
        return ResponseEntity.ok().build();
    }

}

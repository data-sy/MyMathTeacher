package com.mmt.diagnosis.dto.users;

import com.mmt.diagnosis.jwt.JwtToken;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    public static TokenDTO from(JwtToken token) {
        if(token == null) return null;

        return TokenDTO.builder()
                .grantType(token.getGrantType())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}

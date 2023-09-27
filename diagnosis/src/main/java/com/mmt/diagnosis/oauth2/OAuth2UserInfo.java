package com.mmt.diagnosis.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo {

    // protected : 추상클래스를 상속받는 클래스에서만 사용할 수 있도록
    protected Map<String, Object> attributes;

    public abstract String getOAuth2Id();
    public abstract String getEmail();
    public abstract String getName();

}


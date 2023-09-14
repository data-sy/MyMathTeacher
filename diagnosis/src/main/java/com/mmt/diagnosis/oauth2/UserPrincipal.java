package com.mmt.diagnosis.oauth2;

import com.mmt.diagnosis.domain.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails, OAuth2User {

    private Users user;

    private Map<String, Object> oauthUserAttributes;

    public UserPrincipal(Users user) {
        this.user = user;
    }

    public UserPrincipal(Users user, Map<String, Object> oauthUserAttributes) {
        this.user = user;
        this.oauthUserAttributes = oauthUserAttributes;
    }

    @Override
    public String getName() {
        return String.valueOf(user.getUserEmail());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(oauthUserAttributes);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getUserAuthoritySet().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(user.getUserEmail());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    @Nullable
//    @SuppressWarnings("unchecked")
//    public <A> A getAttribute(String name) {
//        return (A) oauthUserAttributes.get(name);
//    }

}

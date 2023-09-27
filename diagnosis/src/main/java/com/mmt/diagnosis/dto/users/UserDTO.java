package com.mmt.diagnosis.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mmt.diagnosis.domain.Authority;
import com.mmt.diagnosis.domain.Users;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userEmail;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;

    private String userName;

    private String userPhone;

    private Set<Authority> authoritySet;

    public static UserDTO from(Users user) {
        if(user == null) return null;

        return UserDTO.builder()
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .userPhone(user.getUserPhone())
                .authoritySet(user.getUserAuthoritySet().stream()
                        .map(userAuthority -> Authority.builder().authorityName(userAuthority.getAuthority().getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

}

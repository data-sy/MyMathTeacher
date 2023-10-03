package com.mmt.diagnosis.service.users;

import com.mmt.diagnosis.domain.Authority;
import com.mmt.diagnosis.domain.UserAuthority;
import com.mmt.diagnosis.domain.Users;
import com.mmt.diagnosis.oauth2.AuthProvider;
import com.mmt.diagnosis.oauth2.OAuth2UserInfo;
import com.mmt.diagnosis.oauth2.OAuth2UserInfoFactory;
import com.mmt.diagnosis.oauth2.UserPrincipal;
import com.mmt.diagnosis.repository.userAuthority.UserAuthorityRepository;
import com.mmt.diagnosis.repository.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UsersRepository usersRepository;
    private final UserAuthorityRepository userAuthorityRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // 최종적으로 여기서 principal 객체가 생성되면 돼
        return processOAuth2User(userRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        //OAuth2 로그인 플랫폼 구분
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
        System.out.println("인포에 있는 데이터들 authProvider : " + authProvider);
        System.out.println("인포에 있는 데이터들 getOAuth2Id : " + oAuth2UserInfo.getOAuth2Id());
        System.out.println("인포에 있는 데이터들 getName : " + oAuth2UserInfo.getName());
        System.out.println("인포에 있는 데이터들 getEmail : " + oAuth2UserInfo.getEmail());
        System.out.println("인포에 있는 데이터들 getAttributes : " + oAuth2UserInfo.getAttributes().toString());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        Users user = usersRepository.findOneWithAuthoritiesByUserEmail(oAuth2UserInfo.getEmail()).orElse(null);
        //이미 가입된 경우
        if (user != null) {
            if (!user.getAuthProvider().equals(authProvider)) {
                throw new RuntimeException("Email already signed up.");
            }
            // 유저가 있고, 프로바이더도 잘 맞으면 실행 => 즉 정상적으로 로그인 했을 때 실행하는 부분
            // 업데이트가 왜 있는거지?..서드파티 쪽에서 수정사항이 있으면 여기에도 수정되도록?
            // 우선 그렇게 처리함. 수정사항 있다면 수정. 아니면 그대로 나가도록
            user = updateUser(user, oAuth2UserInfo);
        }
        //가입되지 않은 경우
        else {
            user = registerUser(authProvider, oAuth2UserInfo);
        }
        return new UserPrincipal(user, oAuth2UserInfo.getAttributes());
    }

    private Users registerUser(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) {
        // OAuth회원 회원가입
        // 리팩토링 : UserService의 signup메서드와 유사하므로 나중에 둘 합쳐도 될 듯
        System.out.println("회원가입 authProvider : " + authProvider);
        System.out.println("회원가입 getOAuth2Id : " + oAuth2UserInfo.getOAuth2Id());
        System.out.println("회원가입 getName : " + oAuth2UserInfo.getName());
        System.out.println("회원가입 getEmail : " + oAuth2UserInfo.getEmail());
        System.out.println("회원가입 getAttributes : " + oAuth2UserInfo.getAttributes().toString());
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        UserAuthority userAuthority = UserAuthority.builder()
                .authority(authority)
                .build();
        Users user = Users.builder()
                .userEmail(oAuth2UserInfo.getEmail())
                .userName(oAuth2UserInfo.getName())
                .oauth2Id(oAuth2UserInfo.getOAuth2Id())
                .authProvider(authProvider)
                .userAuthoritySet(Collections.singleton(userAuthority))
                .activated(true)
                .build();
        Users saveUser = usersRepository.save(user);

        // user_authority 테이블에 user_id 갱신
        user.setUserId(saveUser.getUserId());
        userAuthority.setUser(user);
        userAuthorityRepository.save(userAuthority);

        return saveUser;
    }

    private Users updateUser(Users user, OAuth2UserInfo oAuth2UserInfo) {
        if (!user.getUserEmail().equals(oAuth2UserInfo.getEmail()) || !user.getUserName().equals(oAuth2UserInfo.getName()) || !user.getOauth2Id().equals(oAuth2UserInfo.getOAuth2Id())) {
            user.setUserEmail(oAuth2UserInfo.getEmail());
            user.setUserName(oAuth2UserInfo.getName());
            user.setOauth2Id(oAuth2UserInfo.getOAuth2Id());
            return usersRepository.save(user);
        } else return user;
    }


}


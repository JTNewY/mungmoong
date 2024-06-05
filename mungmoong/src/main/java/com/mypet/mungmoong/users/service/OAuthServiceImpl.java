package com.mypet.mungmoong.users.service;

import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypet.mungmoong.users.dto.CustomUser;
import com.mypet.mungmoong.users.dto.OAuthAttributes;
import com.mypet.mungmoong.users.dto.SocialUserResponse;
import com.mypet.mungmoong.users.dto.UserAuth;
import com.mypet.mungmoong.users.dto.UserSocial;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.mapper.UsersMapper;
import com.mypet.mungmoong.users.dto.NaverUserApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OAuthServiceImpl implements OAuthService {

    private final UsersMapper userMapper;
    private final NaverUserApi naverUserApi;
    private final ObjectMapper objectMapper;

    @Autowired
    public OAuthServiceImpl(UsersMapper userMapper, NaverUserApi naverUserApi, ObjectMapper objectMapper) {
        this.userMapper = userMapper;
        this.naverUserApi = naverUserApi;
        this.objectMapper = objectMapper;
    }

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("::::::::::::::: OAuthServiceImpl - loadUser() :::::::::::::::");
        log.info("OAuth 사용자 정보를 전달받아 OAuth2User 객체로 변환합니다.");

        // 1️⃣ 주요 정보 추출
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                                                  .getUserInfoEndpoint().getUserNameAttributeName();

        log.info("★★★★★ 주요 정보 ★★★★★");
        log.info("****** registrationId : " + registrationId);
        log.info("****** userNameAttributeName : " + userNameAttributeName);
        log.info("****** attributes : " + attributes);

        // 2️⃣ OAuthAttributes 객체 생성
        OAuthAttributes oAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, attributes);
        log.info("****** oAuthAttributes : " + oAuthAttributes);

        String provider = registrationId;

        log.info(":::::::::::::::::::::::::::::::::::::::::::::");
        log.info(provider + "로 로그인 합니다.");
        log.info(":::::::::::::::::::::::::::::::::::::::::::::");

        // 3️⃣ 회원 가입 또는 정보 갱신
        UserSocial userSocial = new UserSocial();
        userSocial.setSocialPlatform(provider);
        userSocial.setSocialId(oAuthAttributes.getId());
        userSocial.setName(oAuthAttributes.getName());
        userSocial.setMail(oAuthAttributes.getMail());

        Users joinedUser = null;
        try {
            joinedUser = userMapper.selectBySocial(userSocial);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (joinedUser == null) {
            log.info("***** 소셜 회원 가입 *****");
            try {
                join(userSocial, oAuthAttributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info("***** 소셜 회원 정보 갱신 *****");
            log.info("joinedUser : " + joinedUser);

            UserSocial joinedUserSocial = null;
            try {
                joinedUserSocial = userMapper.selectSocial(userSocial);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (joinedUserSocial != null) {
                try {
                    update(joinedUserSocial, oAuthAttributes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Users user = new Users();
        try {
            user = userMapper.selectBySocial(userSocial);
            log.info("***** 가입된 소셜 사용자 정보 *****");
            log.info(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CustomUser(user, oAuthAttributes.getAttributes(), oAuthAttributes.getNameAttributeKey());
    }

    @Override
    public int join(UserSocial userSocial, OAuthAttributes oAuthAttributes) throws Exception {
        Users joinedUser = userMapper.selectBySocial(userSocial);

        int result = 0;
        String userId = userSocial.getSocialPlatform() + "_" + userSocial.getSocialId();
        if (joinedUser == null) {
            Users user = new Users();
            user.setUserId(userId);
            user.setName(oAuthAttributes.getName());
            user.setMail(oAuthAttributes.getMail());
            user.setPassword(UUID.randomUUID().toString());
            result = userMapper.join(user);

            UserAuth userAuth = new UserAuth();
            userAuth.setAuth("ROLE_USER");
            userAuth.setUserId(userId);
            userMapper.insertAuth(userAuth);
        }
        if (result > 0) {
            userSocial.setUserId(userId);
            userSocial.setName(oAuthAttributes.getName());
            userSocial.setMail(oAuthAttributes.getMail());
            result += userMapper.insertSocial(userSocial);
        }
        return result;
    }

    @Override
    public int update(UserSocial userSocial, OAuthAttributes oAuthAttributes) throws Exception {
        int result = 0;

        String name = userSocial.getName();
        String email = userSocial.getMail();

        if (!name.equals(oAuthAttributes.getName())) name = oAuthAttributes.getName();
        if (!email.equals(oAuthAttributes.getMail())) email = oAuthAttributes.getMail();

        userSocial.setName(name);
        userSocial.setMail(email);

        result = userMapper.updateSocial(userSocial);

        return result;
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) throws Exception {
        Map<String, String> headers = Map.of("Authorization", "Bearer " + accessToken);
        ResponseEntity<String> response = naverUserApi.getUserInfo(headers);
        return objectMapper.readValue(response.getBody(), SocialUserResponse.class);
    }
}

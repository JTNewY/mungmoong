package com.mypet.mungmoong.users.service;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.mypet.mungmoong.users.dto.OAuthAttributes;
import com.mypet.mungmoong.users.dto.SocialUserResponse;
import com.mypet.mungmoong.users.dto.UserSocial;

/**
 * 📄 OAuth2UserService
 * - OAuth 2.0 인증 흐름에서 사용자 정보를 처리를 위한 인터페이스
 * 
 * 🎫 loadUser 
 * ✅ provider(공급자:카카오,네이버,구글)로부터 사용자 정보를 가져와서 OAuth2User 객체로 변환
 */
public interface OAuthService extends OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    // ✨👩‍💼 소셜 회원 가입
    public int join(UserSocial userSocial, OAuthAttributes oAuthAttributes) throws Exception;

    // 소셜 회원 수정
    public int update(UserSocial userSocial, OAuthAttributes oAuthAttributes) throws Exception;

    public SocialUserResponse getUserInfo(String accessToken) throws Exception;
}

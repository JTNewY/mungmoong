package com.mypet.mungmoong.users.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomUser extends DefaultOAuth2User implements UserDetails {

    // 사용자 DTO
    private Users user;

    public CustomUser(Users user) {
        super(user.getAuthList().stream()
                 .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                 .collect(Collectors.toList()),
              convertUserToAttributes(user),
              "userId"); // 예시로 nameAttributeKey를 "userId"로 설정
        this.user = user;
    }

    public CustomUser(Users user, Map<String, Object> attributes, String nameAttributeKey) {
        super(user.getAuthList().stream()
                 .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                 .collect(Collectors.toList()),
              attributes,
              nameAttributeKey);
        this.user = user;
    }

    private static Map<String, Object> convertUserToAttributes(Users user) {
        // 필요한 사용자 속성을 맵으로 변환
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("userId", user.getUserId());
        // attributes.put("password", user.getPassword());
        attributes.put("name", user.getName());
        attributes.put("birth", user.getBirth());
        attributes.put("address", user.getAddress());
        attributes.put("mail", user.getMail());
        attributes.put("phone", user.getPhone());
        attributes.put("regDate", user.getRegDate());
        attributes.put("updDate", user.getUpdDate());
        attributes.put("enabled", user.getEnabled());
        attributes.put("role", user.getRole());
        attributes.put("gender", user.getGender());
        // 필요한 경우 더 많은 속성을 추가할 수 있습니다.
        return attributes;
    }

    /**
     * 🔐 권한 정보 메소드
     * ✅ UserDetails 를 CustomUser 로 구현하여,
     *    Spring Security 의 User 대신 사용자 정의 인증 객체(CustomUser)로 적용
     * ⚠ CustomUser 적용 시, 권한을 사용할 때는 'ROLE_' 붙여서 사용해야 한다.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthList().stream()
                                 .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                                 .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
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
        return user.getEnabled() != 0;
    }
}

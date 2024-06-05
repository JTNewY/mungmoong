package com.mypet.mungmoong.users.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String mail;
    private String picture;
    private String id;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String mail, String picture, String id) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.mail = mail;
        this.picture = picture;
        this.id = id;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("kakao".equals(registrationId)) {
            return ofKakao(userNameAttributeName, attributes);
        }

        return null;
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) profile.get("nickname"))
                .mail((String) kakaoAccount.get("email"))
                .picture((String) profile.get("thumbnail_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .id(String.valueOf(attributes.get(userNameAttributeName)))
                .build();
    }
}

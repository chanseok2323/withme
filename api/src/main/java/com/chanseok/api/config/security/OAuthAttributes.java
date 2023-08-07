package com.chanseok.api.config.security;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private String registrationId;
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;

    @Builder
    public OAuthAttributes(
            String registrationId,
            Map<String, Object> attributes,
            String nameAttributeKey,
            String email
    ) {
        this.registrationId = registrationId;
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
    }

    public static OAuthAttributes of(
            String registrationId,
            String nameAttributeKey,
            Map<String, Object> attributes
    ) {
        return ofKakao(registrationId, nameAttributeKey, attributes);   // 네이버 등 추가 예정
    }

    private static OAuthAttributes ofKakao(
            String registrationId,
            String nameAttributeKey,
            Map<String, Object> attributes
    ) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        return OAuthAttributes
                .builder()
                    .registrationId(registrationId)
                    .email((String) response.get("email"))
                    .nameAttributeKey(nameAttributeKey)
                    .attributes(attributes)
                .build();
    }
}

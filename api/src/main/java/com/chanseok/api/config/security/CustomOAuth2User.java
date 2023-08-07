package com.chanseok.api.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.*;

public class CustomOAuth2User implements OAuth2User, Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final Set<GrantedAuthority> authorities;
    private final OAuthAttributes oAuthAttributes;

    public CustomOAuth2User(
            Set<GrantedAuthority> authorities,
            OAuthAttributes oAuthAttributes
    ) {
        this.authorities = (authorities != null)
                ? Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities(authorities)))
                : Collections.unmodifiableSet(new LinkedHashSet<>(AuthorityUtils.NO_AUTHORITIES));
        this.oAuthAttributes = oAuthAttributes;
    }

    private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                Comparator.comparing(GrantedAuthority::getAuthority)
        );
        sortedAuthorities.addAll(authorities);
        return sortedAuthorities;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return oAuthAttributes.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public OAuthAttributes getoAuthAttributes() {
        return oAuthAttributes;
    }

    @Override
    public String getName() {
        return this.getAttribute(this.oAuthAttributes.getNameAttributeKey()).toString();
    }
}

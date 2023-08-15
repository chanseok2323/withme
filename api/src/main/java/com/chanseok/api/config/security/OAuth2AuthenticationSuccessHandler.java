package com.chanseok.api.config.security;

import com.chanseok.rdscore.domain.Member;
import com.chanseok.rdscore.repository.MemberRepository;
import com.chanseok.webcore.config.token.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        OAuthAttributes oAuthAttributes = customOAuth2User.getoAuthAttributes();
        String email = oAuthAttributes.getEmail();

        Member findByMember = memberRepository.findByEmail(email);
        if(findByMember == null) {
            memberRepository.save(Member.builder()
                                            .email(oAuthAttributes.getEmail())
                                        .build());
        }

        String accessToken = tokenProvider.generateToken(email);
        String redirectUrl = redirectUrl(accessToken);

        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    private String redirectUrl(String accessToken) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/")
                .queryParam("accessToken", accessToken)
                .build()
                .toUriString();
    }
}

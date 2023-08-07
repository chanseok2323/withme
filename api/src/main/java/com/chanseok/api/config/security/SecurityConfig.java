package com.chanseok.api.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2UserService oAuth2UserService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private static final String permitAllResource[] = {"/oauth2/**", "/login/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> {
                                authorizationManagerRequestMatcherRegistry.requestMatchers(permitAllResource).permitAll();
                                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .sessionManagement(
                        sessionManagementConfigurer ->
                                sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2Login(oAuth2LoginConfigurer -> {
                    oAuth2LoginConfigurer.successHandler(authenticationSuccessHandler);
                    oAuth2LoginConfigurer.userInfoEndpoint(
                            userInfoEndpointConfig -> userInfoEndpointConfig.userService(oAuth2UserService)
                    );
                })
                .build();
    }
}

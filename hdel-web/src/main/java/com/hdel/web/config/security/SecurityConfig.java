package com.hdel.web.config.security;

import com.hdel.web.auth.CustomOAuth2UserService;
import com.hdel.web.auth.OAuth2AuthenticationSuccessHandler;
import com.hdel.web.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
//oauth2 추가
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //Spring boot 2.7에 따른 오류 발생 추가
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/","/h2-console/**"
                            , "/favicon.ico"
                            , "/swagger-resources/**"
                            , "/swagger*/**"
                            , "/v3/api-docs/"
                            , "/swagger-ui.html"
                            , "/v2/api-docs"
                            , "/webjars/**"
                            , "/api/v1/member/**"
                            , "/test/*"
                    ).permitAll()
                    //.antMatchers("/api/v1/**").hasRole(Role.USER.name()) // user 권한 보유자
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                    .and()
                        .successHandler(oAuth2AuthenticationSuccessHandler);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico");

        // swagger
        web.ignoring().antMatchers(
                "/v2/api-docs",  "/configuration/ui",
                "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**","/swagger/**",
                "/v3/api-docs/"
            );
    }
}


package com.hdel.web.config;

import com.hdel.web.service.member.MemberService;
import com.hdel.web.util.TokenRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final TokenRequestFilter tokenRequestFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests() // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
                .anyRequest().permitAll()
                .and() // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() // form 기반의 로그인에 대해 비활성화 한다.
                .formLogin()
                .disable()
                .addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //.addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter.class)

        http.cors();
    }

}
    /* OLD
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    //.antMatchers("/", "/home", "/login", "/swagger-ui/", "/*").permitAll()
                    //.antMatchers("/hello").hasRole("USER")
                    //.anyRequest().authenticated()
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    *//*****
                     * custom page
                    .loginPage("/view/login")
                    .loginProcessingUrl("/loginProc")
                    .usernameParameter("id")
                    .passwordParameter("pw")
                    .defaultSuccessUrl("/view/dashboard", true)
                     *****//*
                    //.loginPage("/login")
                    //.permitAll()
                    .and()
                .logout();
    }*/

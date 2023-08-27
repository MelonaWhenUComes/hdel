package com.hdel.web.config;

import com.hdel.web.domain.member.Role;
import com.hdel.web.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig //extends WebSecurityConfigurerAdapter
{
//
//    private final MemberService memberService;
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    }
//
//    //oauth2 추가
//    //private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        /******* All Authority
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                ;
//         **/
//        /** OAUTH2 미사용시
//        http.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/h2-console/**", "/favicon.ico").permitAll()
//                .anyRequest().authenticated()
//                .and().logout().logoutSuccessUrl("/").permitAll()
//                .and().headers().frameOptions().sameOrigin()
//                .and().csrf().disable();
//        ***/
//        /*** OAUTH 사용시
//        http.csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                    .authorizeRequests()
//                    .antMatchers("/","/h2-console/**", "/favicon.ico").permitAll()
//                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // user 권한 보유자
//                    .anyRequest().authenticated()
//                .and()
//                    .logout()
//                        .logoutSuccessUrl("/")
//                .and()
//                    .oauth2Login()
//                        .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//         */
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers("/static/js/**"
////                ,"/static/css/**"
////                ,"/static/img/**"
////                ,"/static/frontend/**"
////                /**SWAGGER**/
////                ,"/swagger-ui/**"
////        );
//
//
//        web.ignoring().antMatchers("/**");
//        //web.ignoring().antMatchers("/");
//    }

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


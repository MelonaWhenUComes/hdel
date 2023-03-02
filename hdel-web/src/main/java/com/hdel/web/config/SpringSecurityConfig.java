package com.hdel.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

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
                    /*****
                     * custom page
                    .loginPage("/view/login")
                    .loginProcessingUrl("/loginProc")
                    .usernameParameter("id")
                    .passwordParameter("pw")
                    .defaultSuccessUrl("/view/dashboard", true)
                     *****/
                    //.loginPage("/login")
                    //.permitAll()
                    .and()
                .logout();
    }
}

package com.project.recommandBook.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private final RegisteredUserDetailService registeredUserDetailService;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/views/home", "/views/login", "/views/signUp", "/css/**", "/js/**", "/assets/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/views/login")
                    //.failureUrl() 로그인 실패 주소
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/views/logout");

    }

    @Autowired
    public void configuration(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(registeredUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }
}

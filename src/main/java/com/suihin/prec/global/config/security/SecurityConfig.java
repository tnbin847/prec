package com.suihin.prec.global.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 비밀번호 암호화를 위한 {@link PasswordEncoder} 스프링 빈 객체 등록
     *
     * @return  해시 함수를 이용해 비밀번호 암호화를 처리하는 {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SecurityConstants.RESOURCE_MAPPING_MATCHERS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .headers().frameOptions().sameOrigin()
        .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.PUBLICY_REQUEST_MATCHERS).permitAll()
                .anyRequest().authenticated()
        .and()
            .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);
    }
}
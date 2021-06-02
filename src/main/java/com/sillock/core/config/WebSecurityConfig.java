package com.sillock.core.config;


import com.sillock.common.jwt.JwtAccessDiniedHandler;
import com.sillock.common.jwt.JwtAuthenticationEntryPoint;
import com.sillock.common.jwt.JwtProvider;
import com.sillock.common.jwt.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDiniedHandler jwtAccessDiniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //401, 403에러 검사
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDiniedHandler)

                .and()
                .headers().frameOptions().disable()

                .and()
                    .authorizeRequests()
                    .antMatchers("/","/css/**","images/**","/img/**",
                            "/js/**","/favicon.ico").permitAll()
                    .antMatchers("/*/signin", "/*/signin/**", "/*/signup", "/social/**").permitAll()
                    .antMatchers("/user/**").hasRole(Role.USER.name())
                    .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                    .anyRequest().authenticated()

                //JwtSecurityConfig를 통해 jwtRequestFilter 등록
                .and()
                .apply(new JwtSecurityConfig(jwtTokenProvider));

    }

}
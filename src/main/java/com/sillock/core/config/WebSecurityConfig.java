package com.sillock.core.config;


import com.sillock.core.jwt.JwtAccessDiniedHandler;
import com.sillock.core.jwt.JwtAuthenticationEntryPoint;
import com.sillock.core.jwt.JwtTokenProvider;
import com.sillock.core.jwt.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAccessDiniedHandler jwtAccessDiniedHandler;

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
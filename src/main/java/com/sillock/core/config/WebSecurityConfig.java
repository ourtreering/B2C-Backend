package com.sillock.core.config;


import com.sillock.core.auth.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDiniedHandler jwtAccessDiniedHandler;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                //401, 403에러 검사
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDiniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()

                .and()
                .apply(new JwtSecurityConfig(jwtProvider));
//                .authorizeRequests()
//                .antMatchers("/","/css/**","images/**","/img/**",
//                        "/js/**","/favicon.ico").permitAll()
//                .antMatchers("/*/signin", "/*/signin/**", "/*/signup", "/social/**").permitAll()
//                .antMatchers("/user/**").hasRole(Role.USER.name())
//                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
//                .anyRequest().authenticated()
//
//                //JwtSecurityConfig를 통해 jwtRequestFilter 등록
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/api/members/test").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/docs/**", "/webjars/**");
    }

}
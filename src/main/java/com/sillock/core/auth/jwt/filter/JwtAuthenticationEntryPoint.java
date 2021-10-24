package com.sillock.core.auth.jwt.filter;

import com.sillock.core.auth.jwt.component.JwtResolver;
import com.sillock.core.auth.jwt.component.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final JwtValidator jwtValidator;
    private final JwtResolver jwtResolver;

    // TODO: 실제로 동작하지 않음. 어떻게 고치지? jwt 관련 시큐리티 설정 필요
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String message = jwtValidator.setInvalidAuthenticationMessage(jwtResolver.resolveToken(request));

        response.sendError(HttpServletResponse.SC_FORBIDDEN, message);
    }
}
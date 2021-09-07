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

    // TODO: 잘 동작하는 것 확인
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String message = jwtValidator.setInvalidAuthenticationMessage(jwtResolver.resolveToken(request));

        response.sendError(HttpServletResponse.SC_FORBIDDEN, message);
    }
}
package com.sillock.core.auth.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sillock.common.dto.ErrorResponseDto;
import com.sillock.common.message.ExceptionMessage;
import com.sillock.core.auth.jwt.component.JwtResolver;
import com.sillock.core.auth.jwt.component.JwtValidator;
import com.sillock.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtResolver jwtResolver;
    private final JwtValidator jwtValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtResolver.resolveToken(servletRequest);

        if (jwtValidator.validateToken(token)) {
            try {
                setAuthToSecurityContextHolder(token);
            } catch (ResourceNotFoundException e) {
                writeNotFoundResponse(servletResponse, e);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setAuthToSecurityContextHolder(String token) {
        Authentication auth = jwtResolver.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void writeNotFoundResponse(HttpServletResponse response, ResourceNotFoundException ex) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType("application/json");
        ErrorResponseDto errorResponse = ErrorResponseDto.of(ExceptionMessage.JWT_ENTITY_NOT_FOUND);
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(errorResponse);
            response.getWriter().write(json);
        }catch (IOException e){
            log.error("Json failed : ", e);
        }
    }

}
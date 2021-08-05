package com.sillock.config;

import com.sillock.annotation.SillogUser;
import com.sillock.common.EntityFactory;
import com.sillock.core.auth.userDetails.model.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithUserDetailsSecurityContextFactory implements WithSecurityContextFactory<SillogUser> {

    public SecurityContext createSecurityContext(SillogUser withUser) {
        UserDetails principal = new UserDetailsImpl(EntityFactory.basicMemberEntity());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}
package com.github.kotelkov.pms.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER = "Bearer";

    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider=jwtProvider;
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith(BEARER)){
            final String token = authorization.substring(BEARER.length());
            final String username = jwtProvider.getUsernameFromToken(token);
            ofNullable(userDetailsService.loadUserByUsername(username)).
                    ifPresent(x-> SecurityContextHolder.getContext().
                            setAuthentication(new UsernamePasswordAuthenticationToken(username,null,x.getAuthorities())));
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}

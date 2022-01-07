package com.github.kotelkov.pms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    public LoginFilter(JwtProvider jwtProvider, ObjectMapper objectMapper, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.jwtProvider=jwtProvider;
        this.objectMapper=objectMapper;
    }



    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        Credo credo = objectMapper.readValue(httpServletRequest.getInputStream(),Credo.class);
        return getAuthenticationManager().
                authenticate(new UsernamePasswordAuthenticationToken(credo.getLogin(),credo.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain,Authentication authentication){
        String token = prepareJwt(authentication);
        httpServletResponse.addHeader(HttpHeaders.AUTHORIZATION,token);
    }

    private String prepareJwt(Authentication authentication){return jwtProvider.buildToken(authentication.getName());}
}

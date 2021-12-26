package com.github.kotelkov.pms.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kotelkov.pms.security.JwtAuthenticationFilter;
import com.github.kotelkov.pms.security.JwtProvider;
import com.github.kotelkov.pms.security.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Bean
    public LoginFilter loginFilter(JwtProvider jwtProvider, ObjectMapper objectMapper, AuthenticationManager authenticationManager){return new LoginFilter(jwtProvider,objectMapper,authenticationManager);}

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtProvider jwtProvider,UserDetailsService userDetailsService){return new JwtAuthenticationFilter(jwtProvider,userDetailsService);}

    @Bean
    public ObjectMapper objectMapper(){return new ObjectMapper();}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService).
                passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.antMatcher("/**").
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                httpBasic().disable().
                csrf().disable().
                addFilter(loginFilter(jwtProvider,objectMapper(),authenticationManager())).
                addFilterBefore(jwtAuthenticationFilter(jwtProvider,userDetailsService),LoginFilter.class);
    }
}

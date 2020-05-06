package com.setu.biller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf()
    .disable()
    .authorizeRequests()
    .antMatchers(HttpMethod.POST, "/bills/**").authenticated()
    .and()
    .httpBasic().disable()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Apply JWT
    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    http.httpBasic().disable();
  }

  //Create an in memory user.
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("rahul")
        .password("12345678").roles("USER").build();
    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
    userDetailsManager.createUser(user);
    return userDetailsManager;
  }

}

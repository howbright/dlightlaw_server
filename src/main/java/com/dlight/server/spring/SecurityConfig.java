package com.dlight.server.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dlight.client.ClientServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     
     @Autowired ClientServiceImpl clientService;
     
     @Override
     protected void configure(HttpSecurity http) throws Exception {
//          http
//               .csrf().disable()
//               .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//               .formLogin();
    	 
    	 http.csrf().disable()
         .authorizeRequests()
             .antMatchers("/css/**","/js/**","/fonts/**","/images/**","/static/**","/login","/logout","/contract", "**").permitAll()
             .anyRequest().authenticated();
    	  http
          .formLogin()
              .loginPage("/")
              .defaultSuccessUrl("/login")
              .permitAll()
              .and()
          .logout()
              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
              .permitAll()
              .logoutSuccessUrl("/")
              .permitAll();
     }
 
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(clientService);
     }
     
     
     @Bean
     @Override
     public AuthenticationManager authenticationManagerBean() throws Exception {
          return super.authenticationManagerBean();
     }
     
//     @Bean
//     public HttpSessionStrategy httpSessionStrategy() {
//               return new HeaderHttpSessionStrategy();
//     }
}

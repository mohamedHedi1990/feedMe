/*package com.biservices.wakalni.wakalni.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.biservices.wakalni.wakalni.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 @Autowired
	  private CustomUserDetailsService customUserDetailsService;
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	      http.csrf().disable().authorizeRequests()
	              // No need authentication.
	              .antMatchers("/").permitAll() //
	              ///.antMatchers(HttpMethod.GET, "/api/user/login/*").permitAll()
	              // Need authentication.
	              .anyRequest().authenticated()
	              .and().httpBasic();
	      http.cors();

	  }
	  @Override
	  protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
	    authManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	  }
	  
	  @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  @Bean
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }
	  @Override
	  public void configure(WebSecurity web) throws Exception {
	      super.configure(web);
	       web.ignoring().antMatchers("/api/user/login/*");
	       web.ignoring().antMatchers("/*");
	     
	  }
}*/

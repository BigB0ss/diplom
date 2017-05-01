package com.romanov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Kirill_Romanov1 on 23-Mar-17.
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password("user1").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/resources/css/**", "/Registration", "/resources/js/**","/rest/**").permitAll()
                .antMatchers("/home/**").access("hasRole('ROLE_ADMIN')")
                .and().formLogin().loginPage("/").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/home").failureUrl("/").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");


    }
}

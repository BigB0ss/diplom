package com.romanov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by Kirill_Romanov1 on 23-Mar-17.
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password("user1").roles("ADMIN");
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username, password from users where username = ?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/resources/css/**", "/Registration", "/resources/js/**", "/rest/**").permitAll()
                .antMatchers("/home/**").access("hasRole('ROLE_ADMIN')")
                .and().formLogin().loginPage("/").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/home").failureUrl("/").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");


    }

/*    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }*/
}

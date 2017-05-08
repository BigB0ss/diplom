package com.romanov.config;

import com.romanov.model.User;
import com.romanov.repository.UserRepository;
import com.romanov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kirill on 08.05.2017.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails =  userService.loadUserByUsername(userName);
        User user = userRepository.getUserByUserName(userName);
        if (userDetails == null || user == null) {
            throw new BadCredentialsException("User not found.");
        }
        if (userDetails.getPassword().equals(password)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);

        } else {
            throw new BadCredentialsException("Access denied");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

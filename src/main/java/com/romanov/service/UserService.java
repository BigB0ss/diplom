package com.romanov.service;

import com.romanov.model.User;
import com.romanov.model.UserStudent;
import com.romanov.model.UserTeacher;
import com.romanov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Kirill on 07.05.2017.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }

    public void createNewStudent(User user, UserStudent userStudent) {
        userRepository.createNewStudent(user, userStudent);
    }

    public void createNewTeacher(User user, UserTeacher userTeacher) {
        userRepository.createNewTeacher(user,userTeacher);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
}

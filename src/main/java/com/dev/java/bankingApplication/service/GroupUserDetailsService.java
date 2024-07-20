package com.dev.java.bankingApplication.service;

import com.dev.java.bankingApplication.entity.User;
import com.dev.java.bankingApplication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GroupUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userOptional = userRepository.findByUsername(username);
        return null;
    }
}

package com.lukaspar.ep.service;

import com.lukaspar.ep.exception.UserNotFoundException;
import com.lukaspar.ep.mapper.UserMapper;
import com.lukaspar.ep.model.User;
import com.lukaspar.ep.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return userMapper.userToUserPrincipal(user);
    }
}
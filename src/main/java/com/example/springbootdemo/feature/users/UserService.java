package com.example.springbootdemo.feature.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    public void register(RegisterRequest registerRequest) {
        userRepository.findByUsername(registerRequest.username())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Username already exists: " + registerRequest.username());
                });
        var user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(registerRequest.password());
        user.setEmail(registerRequest.email());
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }


}

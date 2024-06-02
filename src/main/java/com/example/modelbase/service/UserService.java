package com.example.modelbase.service;

import com.example.modelbase.dto.response.RolesResponseDto;
import com.example.modelbase.repository.UserRepository;
import com.example.modelbase.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService
{
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(user.getPassword())
                .roles(user.getAccountType().getName())
                .build();
    }

    public User getUserFromToken(String token)
    {
        String email = jwtService.extractEmail(token);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public RolesResponseDto getRolesFromToken(String token)
    {
        User user = getUserFromToken(token);
        List<String> roles = new LinkedList<>();
        RolesResponseDto response = new RolesResponseDto();
        roles.add(user.getAccountType().getName());
        response.setRoles(roles);
        return response;
    }
}
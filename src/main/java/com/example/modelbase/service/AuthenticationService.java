package com.example.modelbase.service;

import com.example.modelbase.dto.request.SignInRequest;
import com.example.modelbase.dto.request.SignUpRequest;
import com.example.modelbase.dto.response.JwtAuthenticationResponse;
import com.example.modelbase.model.AccountType;
import com.example.modelbase.model.Collection;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.AccountTypeRepository;
import com.example.modelbase.repository.CollectionRepository;
import com.example.modelbase.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public JwtAuthenticationResponse signUp(SignUpRequest request)
    {
        // Check if the email already exists
        if (userRepository.existsByEmail(request.getEmail()))
            throw new IllegalArgumentException("Account with this email already exists!");

        // Check if the username already exists
        if (userRepository.existsByUsername(request.getUsername()))
            throw new IllegalArgumentException("Account with this username already exists!");

        // Validate email format
        if (!isValidEmail(request.getEmail()))
            throw new IllegalArgumentException("Invalid email format!");

        // Validate password complexity
        if (!isValidPassword(request.getPassword()))
            throw new IllegalArgumentException("Password does not meet the requirements!");

        // Check if both passwords match
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new IllegalArgumentException("Passwords do not match!");

        // Fetch default account type
        AccountType defaultAccountType = accountTypeRepository.findByName("USER")
                .orElseThrow(() -> new IllegalArgumentException("Default account type not found"));

        // Hash the password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create a new user
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encodedPassword)
                .accountType(defaultAccountType)
                .created(new Date())
                .build();

        // Save the user to the database
        userRepository.save(user);

        // Create user's collection
        Collection collection = new Collection();
        collection.setUser(user);
        collectionRepository.save(collection);

        // Generate JWT token
        String token = jwtService.generateToken(user);

        // Return the JWT authentication response
        return JwtAuthenticationResponse.builder()
                .token(token)
                .build();
    }

    // Validate email format
    private boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, email);
    }

    // Validate password complexity
    private boolean isValidPassword(String password)
    {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(userDetails);

        return JwtAuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private UserDetails loadUserByUsername(String email)
    {
        return new org.springframework.security.core.userdetails.User(
                email,
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"))
                        .getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList("USER")
        );
    }
}

package com.example.modelbase.security;

import com.example.modelbase.service.JwtService;
import com.example.modelbase.service.UserService;


import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String jwt = null;
        String userEmail = null;

        // Extract JWT token from cookie
        if (request.getCookies() != null)
        {
            for (Cookie cookie : request.getCookies())
            {
                if ("jwtCookie".equals(cookie.getName()))
                {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        // Validate JWT token
        if (!StringUtils.isEmpty(jwt))
        {
            userEmail = jwtService.extractEmail(jwt);
            if (userEmail != null)
            {
                UserDetails userDetails = userService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails))
                {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(userDetails);
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                }
            }
        }
        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }
}

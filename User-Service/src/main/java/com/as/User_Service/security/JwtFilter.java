package com.as.User_Service.security;


import com.as.User_Service.model.User;
import com.as.User_Service.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                   throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println("🛡️ JwtFilter checking path: " + path);

        //  Skip JWT filter for login/register endpoints
        if (path.equals("/api/users/login") || path.equals("/api/users/register")) {
            System.out.println("Skipping JwtFilter for: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByEmail(email).orElse(null);

                if (user != null && jwtUtil.validateToken(token, user)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user, null, List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);

    }
}

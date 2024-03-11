package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtAuthenticationFilter: Inizio esecuzione doFilterInternal");

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String token = getTokenFromRequest(request);

            System.out.println("JwtAuthenticationFilter: Token estratto dalla richiesta: " + token);

            if (StringUtils.hasText(token)) {
                System.out.println("JwtAuthenticationFilter: Token non è vuoto, procedo con la validazione...");

                if (jwtTokenProvider.validateToken(token)) {
                    String username = jwtTokenProvider.getUsername(token);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (userDetails != null) {
                        System.out.println("JwtAuthenticationFilter: UserDetails recuperato correttamente: " + userDetails.toString());

                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        System.out.println("JwtAuthenticationFilter: Utente autenticato con successo.");
                    } else {
                        System.out.println("JwtAuthenticationFilter: Impossibile ottenere l'utente associato al token.");
                    }
                } else {
                    System.out.println("JwtAuthenticationFilter: Il token non è valido.");
                }
            } else {
                System.out.println("JwtAuthenticationFilter: Token vuoto o non presente nella richiesta.");
            }
        } else {
            System.out.println("JwtAuthenticationFilter: Utente già autenticato, ignorando il filtro.");
        }

        System.out.println("JwtAuthenticationFilter: Fine esecuzione doFilterInternal");

        filterChain.doFilter(request, response);
    }

}

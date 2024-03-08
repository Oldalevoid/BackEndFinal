package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
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
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Log per verificare l'inizio dell'esecuzione del filtro
        System.out.println("JwtAuthenticationFilter: Inizio esecuzione doFilterInternal");

        // get JWT token from http request
        String token = getTokenFromRequest(request);

        // Log per verificare se il token è stato estratto correttamente
        System.out.println("JwtAuthenticationFilter: Token estratto dalla richiesta: " + token);

        // validate token
        if (StringUtils.hasText(token)) {
            System.out.println("JwtAuthenticationFilter: Token non è vuoto, procedo con la validazione...");

            if (jwtTokenProvider.validateToken(token)) {
                // get username from token
                String username = jwtTokenProvider.getUsername(token);

                // load the user associated with token
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (userDetails != null) {
                    System.out.println("JwtAuthenticationFilter: UserDetail ottenuto correttamente.");

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

        // Log per verificare la fine dell'esecuzione del filtro
        System.out.println("JwtAuthenticationFilter: Fine esecuzione doFilterInternal");

        filterChain.doFilter(request, response);
    }
}

package top.jshanet.scorpio.framework.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.jshanet.scorpio.framework.security.domain.UserCredentials;
import top.jshanet.scorpio.framework.security.service.JwtTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenService jwtTokenService;
    private final JwtAuthenticationEntryPoint entryPoint;

    @Autowired
    public JwtAuthenticationFilter(UserDetailsService userDetailsService,
                                   JwtTokenService jwtTokenService,
                                   JwtAuthenticationEntryPoint entryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtTokenService.getAuthToken(request);

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }
            restoreAuthentication(request, token);
        } catch (AuthenticationException exception) {
            entryPoint.commence(request, response, exception);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void restoreAuthentication(HttpServletRequest request, String token) {
        UserCredentials credentials = jwtTokenService.getUserCredentialsFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
        if(jwtTokenService.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), token, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
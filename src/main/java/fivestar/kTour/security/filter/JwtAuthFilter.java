package fivestar.kTour.security.filter;

import fivestar.kTour.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request URL = {}", request.getRequestURI());
        log.info("Access Token Value = {}", request.getHeader("Authorization"));

        String jwt = resolveToken(request);

        if(StringUtils.hasText(jwt) && tokenService.isTokenValid(jwt)) {
            setAuthentication(tokenService.getEmailFromToken(jwt));
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String email) {
        Authentication authentication = tokenService.createAuthentication(email);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

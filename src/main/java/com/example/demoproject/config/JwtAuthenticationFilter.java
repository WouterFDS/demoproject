package com.example.demoproject.config;

import com.example.demoproject.service.AuthenticatieService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class JwtAuthenticationFilter  extends OncePerRequestFilter {
    @Qualifier("facade")
    @Autowired
    private AuthenticatieService facade;



    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(Constants.HEADER_STRING);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(Constants.TOKEN_PREFIX)) {
            authToken = header.replace(Constants.TOKEN_PREFIX,"");
            try {
                username = getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                System.out.println("an error occured during getting username from token" );
                System.out.println(e.getLocalizedMessage());
            } catch (ExpiredJwtException e) {
                System.out.println("the token is expired and not valid anymore");
                System.out.println(e.getLocalizedMessage());
            }
        } else {
            System.out.println(req.getHeader(Constants.HEADER_STRING));
            System.out.println("couldn't find bearer string, will ignore the header");
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = facade.loadUserByUsername(username);

            if (validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }

    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Constants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }
}

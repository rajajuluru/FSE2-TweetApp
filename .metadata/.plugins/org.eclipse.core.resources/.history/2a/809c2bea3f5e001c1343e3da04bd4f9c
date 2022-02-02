package com.FlightBooking.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.FlightBooking.jwt.UnAuthorizedExceptions;
import com.FlightBooking.jwt.service.UserService;
import com.FlightBooking.jwt.utilities.JWTUtility;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
       
      System.out.println(httpServletRequest.getRequestURI()+"url");
   
/*      httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
      httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
      httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
      httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
      httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        */
 
        System.out.println(authorization+"authorization");
        String token = null;
        String userName = null;

        if(null != authorization && authorization.startsWith("cts")) {
        	System.out.println("starts with cts and not null");
            token = authorization.substring(3);
            userName = jwtUtility.getUsernameFromToken(token);
            httpServletRequest.setAttribute("userName", userName);
        }

        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = userService.loadUserByUsername(userName);
                
            if(jwtUtility.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        
        
        if(httpServletRequest.getRequestURI().toString().trim().contains("Admin"))
        {
        	if(jwtUtility.getRoleFromToken(token.trim()).equalsIgnoreCase("A"))
        	{
        		
        	}
        	else
        	{
        		System.out.println("exception");
				throw  new UnAuthorizedExceptions();
				
        	}
        }
        System.out.println("inside filter we have SecurityContextHolder.getContext().getAuthentication()"+SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

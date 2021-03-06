package com.juluru.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.juluru.JwtCustomException;
import com.juluru.controller.HomeController;

@Component
public class JwtFilter extends OncePerRequestFilter {


	
	@Autowired
	HomeController controller;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
       
      System.out.println(httpServletRequest.getRequestURI()+"in jwt filter in apigateway");
   
/*      httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
      httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
      httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
      httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
      httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        */
 
        System.out.println(authorization+"authorization"+"    "+httpServletRequest.getContextPath());
        String token = null;
        String userName = null;

//        if(null != authorization && authorization.startsWith("cts")) {
//        	System.out.println("starts with cts and not null");
//            token = authorization.substring(3);
//            userName = jwtUtility.getUsernameFromToken(token);
//          
//        }
//
//        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails
//                    = userService.loadUserByUsername(userName);
//                
//            if(jwtUtility.validateToken(token,userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                        = new UsernamePasswordAuthenticationToken(userDetails,
//                        null, userDetails.getAuthorities());
//
//                usernamePasswordAuthenticationToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//
//        }
        
       
        
    if(!httpServletRequest.getRequestURI().contains("error") && !httpServletRequest.getRequestURI().contains("authenticate") && !httpServletRequest.getRequestURI().contains("register"))
    {
    	Integer validate =new Integer(0);
        try {
			 validate = controller.validate(httpServletRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			validate=0;
		}
        
        if(validate.equals(new Integer(1)))
        {
        	 filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        else
        	
        {
        	String redirectURL = "http://" + httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort() +"/error";
        	System.out.println(redirectURL+"redirectURLredirectURLredirectURL");
        	httpServletResponse.sendRedirect(redirectURL);
        
        	//throw new JwtCustomException("not a valid token");
        //	filterChain.doFilter(httpServletRequest, httpServletResponse);
        	
        	
        
        }
    }
    else
    {
    	filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

       // System.out.println("inside filter we have SecurityContextHolder.getContext().getAuthentication()"+SecurityContextHolder.getContext().getAuthentication());
       
    }
}

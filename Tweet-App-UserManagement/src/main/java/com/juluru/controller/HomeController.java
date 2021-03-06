package com.juluru.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.juluru.models.JwtRequest;
import com.juluru.models.JwtResponse;
import com.juluru.models.ValidateToken;
import com.juluru.services.UserService;
import com.juluru.utilities.JWTUtility;

@RestController
@CrossOrigin("*")
public class HomeController {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@GetMapping("/test")
	public String home() {
		return "Welcome to aws console!!";
	}
	


	@PostMapping("authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		Authentication authenticate = null;
		System.out.println(jwtRequest + "jwtRequest in usermanagement authenticate");
		System.out.println("inside authentication");
		try {
			authenticate=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			//authenticate.

			System.out.println(authenticate.toString());
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			System.out.println("badcredentials");
			return new JwtResponse("invalid credentials", false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//System.out.println(authenticate.toString());
			System.out.println("exception");
			return new JwtResponse("invalid credentials", false);
		}

		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtUtility.generateToken(userDetails);

		return new JwtResponse(token, true);
	}

	@Autowired
	UserService Uservice;

	@GetMapping("validateToken")
	public Integer validate(HttpServletRequest httpServletRequest) throws Exception {

		String authorization = httpServletRequest.getHeader("Authorization");
		System.out.println(authorization + "jwt token");

		System.out.println(authorization + "authorization in usermanagement api");

		try {
			String token = authorization.substring(3);
			Boolean validateToken = jwtUtility.validateToken(token,
					Uservice.loadUserByUsername(jwtUtility.getUsernameFromToken(token)));
			if (validateToken) {
				return new Integer(1);
			} else {
				return new Integer(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new Integer(0);
		}
	}

	@GetMapping("getuserid")
	public String getUserId(HttpServletRequest request) {

		System.out.println("authorization getUserid" + request.getHeader("Authorization"));
		String usernameFromToken = jwtUtility.getUsernameFromToken(request.getHeader("Authorization").trim());
		return usernameFromToken;

	}
	
	@GetMapping("getuserrole")
	public String getuserrole(HttpServletRequest request) {

		System.out.println("authorization getUserid" + request.getHeader("Authorization"));
		String usernameFromToken = jwtUtility.getRoleFromToken(request.getHeader("Authorization").trim().substring(3));
		return usernameFromToken;

	}
	

@PostMapping("/validateTokenByRestApi")
public JwtResponse validateTokenByRestApi(@RequestBody 	ValidateToken tokendata)
{
	System.out.println("validateTokenByRestApi method is called");
	JwtResponse res=new JwtResponse();
	Boolean validateTokenByRestApi =false;
	try{
		System.out.println(tokendata+"tokendatatokendatatokendatatokendata");
		validateTokenByRestApi=jwtUtility.validateTokenByRestApi(tokendata.getToken().trim().substring(3), tokendata.getUserid());
	System.out.println(validateTokenByRestApi+"boolean from a method");
		if(validateTokenByRestApi)
		{
			res.setStatus(true);
			res.setdata("validated");
			return res;
		}
		else
		{
			res.setStatus(false);
			res.setdata("invalid token");
			return res;
		}
	}catch (Exception e) {
		// TODO: handle exception
		res.setStatus(false);
		res.setdata(e.getMessage());
		e.printStackTrace();
		return res;
	}
	
}

	}





package com.juluru.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.juluru.models.JwtRequest;
import com.juluru.models.JwtResponse;
import com.juluru.services.ServiceUtilities;

@RestController
@CrossOrigin("*")
public class HomeController {


//	@Autowired
//	RestTemplate template;

	@Autowired
	ServiceUtilities serviceURLS;

	@GetMapping("/test")
	public String home() {
		return "Welcome to aws console!!";
	}
	
	@GetMapping("/error")
	public JwtResponse error() {
		
		JwtResponse  response =new JwtResponse("invalid token", false);
		return response;
	}

	/*
	 * @PostMapping("authenticate") public JwtResponse authenticate(@RequestBody
	 * JwtRequest jwtRequest) throws Exception { Authentication authenticate =
	 * null; System.out.println(jwtRequest + "jwtRequest");
	 * System.out.println("inside authentication"); try {
	 * authenticate=authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
	 * jwtRequest.getPassword())); //authenticate.
	 * 
	 * System.out.println(authenticate.toString()); } catch
	 * (BadCredentialsException e) { e.printStackTrace();
	 * System.out.println("badcredentials"); return new
	 * JwtResponse("invalid credentials", false); } catch (Exception e) { //
	 * TODO: handle exception e.printStackTrace();
	 * //System.out.println(authenticate.toString());
	 * System.out.println("exception"); return new
	 * JwtResponse("invalid credentials", false); }
	 * 
	 * final UserDetails userDetails =
	 * userService.loadUserByUsername(jwtRequest.getUsername());
	 * 
	 * final String token = jwtUtility.generateToken(userDetails);
	 * 
	 * return new JwtResponse(token, true); }
	 */

	@PostMapping("authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		RestTemplate template= new RestTemplate();
		System.out.println(jwtRequest + "jwtRequest");
		System.out.println("inside authentication of api gatweay ");
		ResponseEntity<JwtResponse> postForEntity = template.postForEntity(
				serviceURLS.getEurkaClientBaseUriofUserManagement() + "/authenticate", jwtRequest, JwtResponse.class);
		System.out.println(postForEntity + "fetching from authenticate url in user management");
		JwtResponse body = postForEntity.getBody();
		return body;
	}


	//
	// @GetMapping("validateToken")
	// public Integer validate(HttpServletRequest httpServletRequest) throws
	// Exception {
	//
	// String authorization = httpServletRequest.getHeader("Authorization");
	// System.out.println(authorization + "jwt token");
	//
	// System.out.println(authorization + "authorization");
	//
	// try {
	// String token = authorization.substring(3);
	// Boolean validateToken = jwtUtility.validateToken(token,
	// Uservice.loadUserByUsername(jwtUtility.getUsernameFromToken(token)));
	// if (validateToken) {
	// return new Integer(1);
	// } else {
	// return new Integer(0);
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// return new Integer(0);
	// }
	// }

	@GetMapping("validateToken")
	public Integer validate(HttpServletRequest httpServletRequest) throws Exception {
		RestTemplate template= new RestTemplate();
		String authorization = httpServletRequest.getHeader("Authorization");
		System.out.println(authorization + "jwt token");

		System.out.println(authorization + "authorization");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<Integer> response = template.exchange(
				serviceURLS.getEurkaClientBaseUriofUserManagement()+"/validateToken", HttpMethod.GET, request,
				Integer.class);
		System.out.println(response + "response from validate token from usermanagement");
		return response.getBody();

	}

//	@GetMapping("getuserid")
//	public String getUserId(HttpServletRequest request) {
//
//		System.out.println("authorization getUserid" + request.getHeader("Authorization"));
//		String usernameFromToken = jwtUtility.getUsernameFromToken(request.getHeader("Authorization").trim());
//		return usernameFromToken;
//
//	}

//	@GetMapping("getuserrole")
//	public String getuserrole(HttpServletRequest request) {
//
//		System.out.println("authorization getUserid" + request.getHeader("Authorization"));
//		String usernameFromToken = jwtUtility.getRoleFromToken(request.getHeader("Authorization").trim().substring(3));
//		return usernameFromToken;
//
//	}

/*	@PostMapping("/validateTokenByRestApi")
	public JwtResponse validateTokenByRestApi(@RequestBody ValidateToken tokendata) {
		System.out.println("validateTokenByRestApi method is called");
		JwtResponse res = new JwtResponse();
		Boolean validateTokenByRestApi = false;
		try {
			System.out.println(tokendata + "tokendatatokendatatokendatatokendata");
			validateTokenByRestApi = jwtUtility.validateTokenByRestApi(tokendata.getToken().trim().substring(3),
					tokendata.getUserid());
			System.out.println(validateTokenByRestApi + "boolean from a method");
			if (validateTokenByRestApi) {
				res.setStatus(true);
				res.setdata("validated");
				return res;
			} else {
				res.setStatus(false);
				res.setdata("invalid token");
				return res;
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.setStatus(false);
			res.setdata(e.getMessage());
			e.printStackTrace();
			return res;
		}

	}*/

}

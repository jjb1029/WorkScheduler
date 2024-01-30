package com.jorden.WorkScheduler.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jorden.WorkScheduler.User;
import com.jorden.WorkScheduler.dto.AuthCredentialsRequest;
import com.jorden.WorkScheduler.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/auth", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
		
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest req) {
		try {
			System.out.println(req.getUsername());
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
			
			User user = (User) authenticate.getPrincipal();
			
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(user))
					.body(user); // <- may expose password
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}

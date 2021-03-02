package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.RegisterRequest;
import lombok.AllArgsConstructor;
import service.AuthService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	
	private AuthService authService;
	
	@PostMapping("/mica")
	public ResponseEntity<String>  mica() {
		
		//authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful",
				HttpStatus.OK);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String>  singup(@RequestBody RegisterRequest registerRequest) {
		
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful",
				HttpStatus.OK);
		
	}
	
	
}

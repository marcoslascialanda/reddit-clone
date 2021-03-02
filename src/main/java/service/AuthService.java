package service;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dto.RegisterRequest;
import lombok.AllArgsConstructor;
import model.NotificationEmail;
import model.User;
import model.VerificationToken;
import repository.UserRepository;
import repository.VerificationTokenRepository;

@Service
@AllArgsConstructor
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private MailService mailService;
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
		
		User user = new User();
		
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		
		userRepository.save(user);
		
		
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Plase Activate your Account",
				user.getEmail(),"Thank you for signing up to Spring Reddit, " +
		                "please click on the below url to activate your account : " +
		                "http://localhost:8080/api/auth/accountVerification/" + token));
		
	}

	


	private  String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setUser(user);
		
		verificationTokenRepository.save(verificationToken);
		
		return token;
		
		
	}
	
}

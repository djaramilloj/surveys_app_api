
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

@Controller
public class MainController {
	@Autowired

	private UserRepository userRepository;

	@PostMapping(path="/auth/signup")
	// FALTA TIMESTAMP
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody User signUp (@RequestBody User user) {
		String plainPassword = user.getPassword();
		String bodyName = user.getName();
		String bodyEmail = user.getEmail();
		// encode password
		int strength = 5;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);
		String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);
		User newUser = new User();
		newUser.setName(bodyName);
		newUser.setEmail(bodyEmail);
		newUser.setPassword(encodedPassword);
		userRepository.save(newUser);
		return newUser;
	}

	@PostMapping(path="/auth/login")
	public @ResponseBody User logIn (@RequestBody User user) {
		String plainPassword = user.getPassword();
		String bodyEmail = user.getEmail();
		User result = userRepository.findByEmail(bodyEmail);
		if (result != null) {
			// decode password
			int strength = 5;
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);
			boolean passwordMatch = bCryptPasswordEncoder.matches(plainPassword, result.getPassword());
			System.out.println(passwordMatch);
			if(passwordMatch) {
				var cookie = ResponseCookie.from("email", result.getEmail()).build();
				ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, cookie.toString())
					.build();
			}
		}
		return result;
	}

	// @GetMapping("/hello")
	// public @ResponseBody Iterable<User> getAllUsers() {
    // 	return userRepository.findAll();
  	// }

}
            
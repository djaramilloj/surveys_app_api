
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

	private SurveyRepository surveyRepository;
	private UserRepository userRepository;


	@PostMapping(path="/auth/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseAPI signUp (@RequestBody User user) {
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
		ResponseAPI response = new ResponseAPI();
		response.setStatus(201);
		response.setMessage("User created");
		return response;
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

	@PostMapping(path="/main/survey")
	// make a survey
	public @ResponseBody ResponseAPI sendSurvey (@RequestBody Survey surveySent) {
		String documentNumber = surveySent.getDoc();
		String pcBrandId = surveySent.getBrand();
		String email = surveySent.getEmail();
		String comments = surveySent.getComments();
		ResponseAPI response = new ResponseAPI();
		if (documentNumber == null || pcBrandId == null || email == null || comments == null) {
			response.setStatus(400);
			response.setMessage("Incomplete Information");
			return response;
		} else {
			Survey survey = new Survey();
			survey.setDoc(documentNumber);
			survey.setEmail(email);
			survey.setBrand(pcBrandId);
			survey.setComments(comments);
			surveyRepository.save(survey);
			response.setStatus(201);
			response.setMessage("Survey Sent");
			return response;
		}

	}

	@GetMapping("/main/getsurvey")
	public @ResponseBody Iterable<Survey> getAllSurveys(@RequestParam String email) {
		Iterable<Survey> result = surveyRepository.findByEmail(email);
		return result;
	}
}

            
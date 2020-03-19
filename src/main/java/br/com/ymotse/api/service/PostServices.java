package br.com.ymotse.api.service;

import java.util.Calendar;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ymotse.api.security.TokenProvider;
import br.com.ymotse.api.security.authentication.TokenResponse;
import br.com.ymotse.api.security.authentication.UserForm;
import br.com.ymotse.api.utils.ConverterDates;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@RestController
public class PostServices {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider tokenProvider;

	private static Logger logger = Logger.getLogger(PostServices.class);

	@PostMapping(value = { "/", "" })
	public ResponseEntity<?> user(@Valid @RequestBody UserForm userForm) throws Exception {

		logger.info("POST /signin | " + userForm.getUsername());

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateJwtToken(authentication);

		ConverterDates conveterDates = new ConverterDates();
		String tokenCreated = conveterDates.convertCalendarToStringDateAndTime(Calendar.getInstance());
		String tokenExpiration = tokenProvider.getTimeExpiration();

		TokenResponse tokenResponse = new TokenResponse(jwt, tokenCreated, tokenExpiration);
		return ResponseEntity.ok(tokenResponse);
	}

}

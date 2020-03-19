package br.com.ymotse.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.repository.UserBusinessImpl;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@RestController
public class GetServices {

	@Autowired
	private UserBusinessImpl userBusinessImpl;

	@GetMapping(value = { "/", "" })
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> user() throws Exception {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User login = userBusinessImpl.findByUsername(username).orElseThrow();

		return new ResponseEntity<User>(login, HttpStatus.OK);
	}

	
}

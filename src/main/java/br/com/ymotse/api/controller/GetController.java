package br.com.ymotse.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@RestController
@RequiredArgsConstructor
public class GetController {

	private final UserService userService;

	
	@GetMapping(value = { "/", "" })
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> user() throws Exception {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return new ResponseEntity<User>(userService.findByUsername(username), HttpStatus.OK);
	}

}

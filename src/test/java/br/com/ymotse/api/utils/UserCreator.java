package br.com.ymotse.api.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.ymotse.api.entity.Role;
import br.com.ymotse.api.entity.User;

public class UserCreator {
	
	public static User createUserValidate() {
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(1L, "ROLE_TEST"));
		
		return User.builder()
				.email("setup@mail.com")
				.enabled(true)
				.password("setup123")
				.username("setup")
				.roles(roles)
				.build();
	}
	
	public static UserDetails createUserDetailsValidate() {
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(1L, "ROLE_TEST"));
		
		return User.builder()
				.email("setup@mail.com")
				.enabled(true)
				.password("setup123")
				.username("setup")
				.roles(roles)
				.build();
	}

	public static User createUserToBeSaved() {
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(1L, "ROLE_ADMIN"));
		
		return User.builder()
				.email("test@mail.com")
				.enabled(true)
				.password("123")
				.username("test")
				.roles(roles)
				.build();
	}
	
}

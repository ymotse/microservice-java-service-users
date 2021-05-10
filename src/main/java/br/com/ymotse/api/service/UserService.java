package br.com.ymotse.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ymotse.api.entity.Role;
import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.repository.UserRepository;
import br.com.ymotse.api.security.authentication.UserPrinciple;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final RoleService roleService;

	private final UserRepository userRepository;

	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow();
	}

	public User bootstrap() {

		Role role = roleService.bootstrap();

		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User user = userRepository.findByUsername("ymotse")
				.orElse(new User("ymotse", passwordEncoder.encode("123"), true, "email@mail.com", roles));

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return UserPrinciple.build(findByUsername(username));
	}

}

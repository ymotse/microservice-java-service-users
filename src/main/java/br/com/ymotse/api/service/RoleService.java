package br.com.ymotse.api.service;

import org.springframework.stereotype.Service;

import br.com.ymotse.api.entity.Role;
import br.com.ymotse.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	
	public Role bootstrap() {
		
		final String ROLE_USER = "ROLE_USER";
		
		Role role = roleRepository.findByName(ROLE_USER);
		
		return (role == null) ? roleRepository.save(new Role(ROLE_USER)) : role;
	}
	
	
}

package br.com.ymotse.api.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ymotse.api.entity.Role;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Service
public class RoleBusinessImpl implements RoleRepository {

	@Autowired
	private RoleRepository roleRepository;

	private final String ROLE_USER = "ROLE_USER";
	
	@Override
	public <S extends Role> S save(S entity) {
		return roleRepository.save(entity);
	}

	@Override
	public <S extends Role> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Role> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Role> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Role entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Role> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	public Role bootstrap() {
		
		Role role = findByName(ROLE_USER);
		
		return (role == null) ? save(new Role(ROLE_USER)) : role;
	}

}

package br.com.ymotse.api.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ymotse.api.entity.Role;
import br.com.ymotse.api.entity.User;

/**
 * @author yitshhaq.fukushima
 *
 */
@Service
public class UserBusinessImpl implements UserRepository {

	@Autowired
	private UserRepository loginRepository;

	@Autowired
	private RoleBusinessImpl roleBusinessImpl;

	public Optional<User> findByUsername(String p_username) {
		return loginRepository.findByUsername(p_username);
	}

	@Override
	public <S extends User> S save(S entity) {
		return loginRepository.save(entity);
	}

	@Override
	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
		return loginRepository.saveAll(entities);
	}

	@Override
	public Optional<User> findById(Long id) {
		return loginRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return loginRepository.existsById(id);
	}

	@Override
	public Iterable<User> findAll() {
		return loginRepository.findAll();
	}

	@Override
	public Iterable<User> findAllById(Iterable<Long> ids) {
		return loginRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return loginRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		loginRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		loginRepository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		loginRepository.deleteAll();
	}

	@Override
	public void deleteAll() {
		loginRepository.deleteAll();
	}

	public User bootstrap() {

		Role role = roleBusinessImpl.bootstrap();

		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User user = findByUsername("ymotse")
				.orElse(new User("ymotse", passwordEncoder.encode("123"), true, "email@mail.com", roles));

		return save(user);
	}

}

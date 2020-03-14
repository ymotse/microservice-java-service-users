package br.com.ymotse.api.security.authentication;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.repository.UserBusinessImpl;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Repository
@Transactional
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserBusinessImpl userBusinessImpl;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User login = userBusinessImpl.findByUsername(username).orElseThrow();

		return UserPrinciple.build(login);
	}

}

package br.com.ymotse.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ymotse.api.entity.User;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Repository
interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String user);

}

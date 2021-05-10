package br.com.ymotse.api.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ymotse.api.entity.Role;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

}

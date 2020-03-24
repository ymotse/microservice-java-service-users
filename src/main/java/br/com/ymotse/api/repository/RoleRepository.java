package br.com.ymotse.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ymotse.api.entity.Role;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Repository
interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);
	
}

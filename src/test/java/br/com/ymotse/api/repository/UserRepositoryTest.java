package br.com.ymotse.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.utils.UserCreator;

@DataJpaTest
@DisplayName("Tests for User Repository")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	
	@Test
	@DisplayName("Save returns User when successful")
	void save_whenSuccessful() {
		User userSaved = this.userRepository.save(UserCreator.createUserToBeSaved());

		Assertions.assertThat(userSaved).isNotNull();
		Assertions.assertThat(userSaved.getCont_id()).isNotNull();
		Assertions.assertThat(userSaved.getUsername()).isEqualTo(userSaved.getUsername());
	}

}

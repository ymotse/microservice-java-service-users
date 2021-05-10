package br.com.ymotse.api.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.repository.UserRepository;
import br.com.ymotse.api.utils.UserCreator;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepositoryMock;

	@BeforeEach
	void setup() {
		Optional<User> user = Optional.of(UserCreator.createUserValidate());

		BDDMockito.when(userRepositoryMock.findByUsername(user.get().getUsername()))
			.thenReturn(user);
	}

	
	@Test
	@DisplayName("findByName returns User when successful")
	void findByName_returnsUser_whenSuccessful() {
		User userValidate = UserCreator.createUserValidate();

		User user = userService.findByUsername(userValidate.getUsername());

		Assertions.assertThat(user).isNotNull();
		Assertions.assertThat(user.getCont_id()).isEqualTo(userValidate.getCont_id());
		Assertions.assertThat(user.getUsername()).isEqualTo(userValidate.getUsername());
	}

	@Test
	@DisplayName("findByName returns User when successful")
	void loadUserByUsername_returnsUserDetails_whenSuccessful() {
		User userValidate = UserCreator.createUserValidate();
		
		UserDetails user = userService.loadUserByUsername(userValidate.getUsername());
		
		Assertions.assertThat(user).isNotNull();
		Assertions.assertThat(user.getUsername()).isEqualTo(userValidate.getUsername());
	}

}

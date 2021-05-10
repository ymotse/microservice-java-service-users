package br.com.ymotse.api.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 * @author yitshhaq.fukushima
 *
 */
@Component
@RequiredArgsConstructor
public class InitialData implements ApplicationListener<ContextRefreshedEvent> {

	private final UserService userService;

	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		User user = userService.bootstrap();

		System.out.println("Bootstrap: " + user.getUsername() + " created.");
	}

}

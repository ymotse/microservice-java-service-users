package br.com.ymotse.api.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.repository.UserBusinessImpl;

/**
 * @author yitshhaq.fukushima
 *
 */
@Component
public class InitialData implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserBusinessImpl userBusinessImpl;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		User user = userBusinessImpl.bootstrap();
		
		System.out.println("Bootstrap: " + user.getUsername() + " created.");
	}

}

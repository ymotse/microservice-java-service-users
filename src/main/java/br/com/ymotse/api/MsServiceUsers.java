package br.com.ymotse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class MsServiceUsers {

	public static void main(String[] args) {
		SpringApplication.run(MsServiceUsers.class, args);

	}

}

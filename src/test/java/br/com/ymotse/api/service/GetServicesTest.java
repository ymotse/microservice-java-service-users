package br.com.ymotse.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.URL;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yitshhaq.fukushima
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetServicesTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private final String URI_USER = "/";
	
	@Test
	public void user_test() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 0eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5bW90c2UiLCJlbWFpbCI6Inltb3RzZUBnbWFpbC5jb20iOjE1ODQ1MzYxMTJ9.97lFHi-oktvV97taFa-mZfA5D-RbQjgmH9BYem8Tq-eJ1rOCWKQJ5sghYkda736qW01_oxIJnzpQ");
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> response = testRestTemplate.exchange(new URL("http://localhost:" + port + URI_USER).toString(), HttpMethod.GET, request, String.class);

		
		assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

}

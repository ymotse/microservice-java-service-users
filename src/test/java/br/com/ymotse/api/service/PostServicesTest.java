package br.com.ymotse.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
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

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostServicesTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private final String URI_USER = "/";

	@Test
	public void user_test() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> body = new HashMap<String, String>();
		body.put("username", "ymotse");
		body.put("password", "123");
		String requestBodyData = new ObjectMapper().writeValueAsString(body);

		HttpEntity<String> request = new HttpEntity<>(requestBodyData, headers);

		ResponseEntity<String> response = testRestTemplate.exchange( new URL("http://localhost:" + port + URI_USER).toString(), HttpMethod.POST, request, String.class);

		
		assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertNotNull(response.getBody());

		JSONObject jsonObject = new JSONObject(response.getBody());
		
		assertEquals("Bearer ", jsonObject.get("prefix").toString());
		
		assertNotNull(jsonObject.get("token").toString());
	}

}

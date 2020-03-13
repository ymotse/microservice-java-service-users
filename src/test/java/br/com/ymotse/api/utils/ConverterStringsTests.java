package br.com.ymotse.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@SpringBootTest
public class ConverterStringsTests {

	private ConverterStrings converterStrings;

	private String stringToConverter;

	private List<String> listStringExpected;

	@Before
	public void setup() {
		converterStrings = new ConverterStrings();

		stringToConverter = "ROLE_USER, ROLE_ADMIN, ROLE_MASTER";

		listStringExpected = new ArrayList<String>(Arrays.asList("ROLE_USER", "ROLE_ADMIN", "ROLE_MASTER"));
	}

	@Test
	public void converterStringToListString_test() {

		assertEquals(listStringExpected, converterStrings.converterStringToListString(stringToConverter));

		assertEquals(null, converterStrings.converterStringToListString(""));

		assertEquals(null, converterStrings.converterStringToListString(null));
	}

}

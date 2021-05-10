package br.com.ymotse.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@ExtendWith(SpringExtension.class)
public class ConverterStringsTest {

	private String stringToConverter = "ROLE_USER, ROLE_ADMIN, ROLE_MASTER";

	private List<String> listStringExpected = new ArrayList<String>(
			Arrays.asList("ROLE_USER", "ROLE_ADMIN", "ROLE_MASTER"));


	@Test
	void converterStringToListString_test() {
		ConverterStrings converterStrings = new ConverterStrings();

		assertEquals(listStringExpected, converterStrings.converterStringToListString(stringToConverter));

		assertEquals(null, converterStrings.converterStringToListString(""));

		assertEquals(null, converterStrings.converterStringToListString(null));
	}

}

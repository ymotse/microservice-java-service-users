package br.com.ymotse.api.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yitshhaq.fukushima
 *
 */
public class ConverterStrings {

	public List<String> converterStringToListString(String stringToConverter) {
		if (stringToConverter == null)
			return null;

		List<String> listStringConverted = new ArrayList<String>();

		String[] arrayString = stringToConverter.replace(" ", "").trim().split(",");

		for (String string : arrayString)
			listStringConverted.add(string);

		return stringToConverter.equals("") ? null : listStringConverted;
	}

}

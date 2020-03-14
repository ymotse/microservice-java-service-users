package br.com.ymotse.api.security;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ymotse.api.utils.ConverterDates;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ConverterDates converterDates = new ConverterDates();
		Calendar now = Calendar.getInstance();

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("datetime", converterDates.convertCalendarToStringDateAndTime(now));
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(exception -> exception.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);
	}

}
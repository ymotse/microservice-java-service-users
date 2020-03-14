package br.com.ymotse.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.ymotse.api.security.authentication.UserPrinciple;
import br.com.ymotse.api.utils.ConverterDates;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Component
public class TokenProvider {

	@Value("${app.properties.secret_signature}")
	protected String jwtSecret;

	@Value("${app.properties.token_expiration_minutes}")
	private Integer jwtExpirationMinutes;

	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	
	public String generateJwtToken(Authentication authentication) throws Exception {

		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

		Object[] authorities = userPrincipal.getAuthorities().toArray();

		List<String> roles = new ArrayList<String>();
		for (Object authority : authorities)
			roles.add(String.valueOf(authority));

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.claim("email", userPrincipal.getEmail())
				.claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date((long) (System.currentTimeMillis() + (60000 * jwtExpirationMinutes))))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(jwtSecret.getBytes()))
				.setIssuedAt(new Date())
				.compact();
	}

	protected boolean validateJwtToken(String authToken) throws IOException, ServletException {
		TokenFilter jwtAuthTokenFilter = new TokenFilter();

		try {
			Jwts.parser()
				.setSigningKey(Base64.getEncoder().encodeToString(jwtSecret.getBytes()))
				.parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature.");
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token.");
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT token.");

			jwtAuthTokenFilter.doFilterInternal(null, null, null);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token.");
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}

	public String getTimeExpiration() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date((long) (System.currentTimeMillis() + (60000 * jwtExpirationMinutes))));

		ConverterDates converterDates = new ConverterDates();

		return converterDates.convertCalendarToStringDateAndTime(calendar);
	}

}
package br.com.ymotse.api.security.authentication;

import lombok.Data;
import lombok.NonNull;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Data
public class TokenResponse {

	@NonNull
	private String prefix = "Bearer ";

	@NonNull
	private String token;

	@NonNull
	private String created;

	@NonNull
	private String expiration;

	public TokenResponse(String jwt, String created, String expiration) {
		this.token = jwt;
		this.created = created;
		this.expiration = expiration;
	}

}
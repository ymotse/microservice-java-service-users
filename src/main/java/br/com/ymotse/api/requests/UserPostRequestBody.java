package br.com.ymotse.api.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Data
@NoArgsConstructor
public class UserPostRequestBody {

	@JsonProperty("username")
	@NotNull(message = "Username cannot be null")
	@Size(min = 3, max = 40, message = "Username must be between 3 and 40 characters")
	private String username;

	@JsonProperty("password")
	@NotNull(message = "Password cannot be null")
	@Size(min = 3, max = 40, message = "Password must be between 3 and 40 characters")
	private String password;

}
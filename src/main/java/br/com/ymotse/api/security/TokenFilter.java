package br.com.ymotse.api.security;

import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ymotse.api.entity.Role;
import br.com.ymotse.api.entity.User;
import br.com.ymotse.api.security.authentication.UserPrinciple;
import br.com.ymotse.api.utils.ConverterStrings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
public class TokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException {

		try {
			String jwt = getJwt(request);

			if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
				Claims claims = getClaimsFromJwt(jwt);

				Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

				ConverterStrings converterString = new ConverterStrings();
				List<String> list_roles = converterString.converterStringToListString(claims.get("roles").toString().replace("[", "").replace("]", ""));

				for (String string : list_roles)
					authorities.add(new SimpleGrantedAuthority(string));

				Set<Role> roles = new HashSet<>();
				User login = new User(1L, claims.getSubject(), null, true, claims.get("email").toString(), roles);

				UserDetails userDetails = UserPrinciple.build(login);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());

			logger.error("Exception: " + response.getStatus() + "\n" + e.getMessage());
		}
	}

	public String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}
		return null;
	}

	public Claims getClaimsFromJwt(String token) {

		return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(tokenProvider.jwtSecret.getBytes()))
				.parseClaimsJws(token).getBody();
	}

}

package br.com.ymotse.api.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "username", unique = true, length = 30, nullable = false, updatable = false)
	private String username;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;
	
	@JsonIgnore
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "login_roles", 
      joinColumns = @JoinColumn(name = "CONT_ID"), 
      inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<>();
	
	
	@Override
	@JsonIgnore
	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}

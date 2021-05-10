package br.com.ymotse.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "role_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name", length = 40, unique = true, nullable = false)
	private String name;

	public Role(String name) {
		this.name = name;
	}

}

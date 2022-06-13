package com.mst.auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
		name = "User",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username")
		}
)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 50)
	private String name;

	@NotNull
	@Size(max = 25)
	private String username;
	
	@NotNull
	@Size(max = 120)
	private String password;

	private Integer majorId;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "UserRole",
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleId")
	)
	private Set<Role> roles = new HashSet<>();

	public User(String name, String username, String password, Integer majorId, Set<Role> roles) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.majorId = majorId;
		this.roles = roles;
	}
}

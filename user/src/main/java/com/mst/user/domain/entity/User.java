package com.mst.user.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@NotNull
	@Size(max = 25)
	private String username;
	
	@NotNull
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 50)
	private String name;
	
	private Integer majorId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "UserRole",
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleId")
	)
	private Set<Role> roles = new HashSet<>();

}

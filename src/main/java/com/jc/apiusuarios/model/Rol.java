package com.jc.apiusuarios.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "role_name"))
public class Rol {

	public enum RolStatus {
		ACTIVE, INACTIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "role_name", unique = true, nullable = false, length = 50)
	private String roleName;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private RolStatus status;

	@OneToMany(mappedBy = "role")
	private Set<User> users;

}

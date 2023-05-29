package com.jc.apiusuarios.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	public enum UserStatus {
		ACTIVE, INACTIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Rol role;

	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	@Column(name = "age", nullable = false, length = 3)
	private int age;

	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private UserStatus status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

}

package com.jc.apiusuarios.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class UserDto {
	
	private Integer id;
	private RolDto role;
	private String username;
	private String name;
	private String lastName;
	private LocalDateTime dateOfBirth;
	private int age;
	private String email;
	private String status;
	private LocalDateTime createdAt;

}

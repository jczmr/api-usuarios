package com.jc.apiusuarios.web.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class RolDto {
	
	private Integer id;
	private String roleName;
	private LocalDateTime createdAt;
	private String status;

}

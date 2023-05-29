package com.jc.apiusuarios.service;

import java.util.List;

import com.jc.apiusuarios.model.Rol;
import com.jc.apiusuarios.web.dto.RolDto;

public interface IRolService {
	
	List<RolDto> findAll();
	
	RolDto saveRol(RolDto rolDto);

	RolDto updateRol(RolDto rolDto);

	void deleteRolById(Integer id);

	RolDto getRolById(Integer id);

	RolDto getRolByRoleName(String roleName);

}

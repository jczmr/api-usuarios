package com.jc.apiusuarios.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jc.apiusuarios.model.Rol;
import com.jc.apiusuarios.web.dto.RolDto;

@Mapper(componentModel = "spring")
public interface AutoRolMapper {
	
	RolDto mapToRolDto(Rol rol);

	List<RolDto> mapToRolDtoList(List<Rol> roles);
	
	Rol mapToRol(RolDto rolDto);

}

package com.jc.apiusuarios.service;

import java.util.List;

import com.jc.apiusuarios.mapper.AutoRolMapper;
import com.jc.apiusuarios.model.Rol;
import com.jc.apiusuarios.web.dto.RolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jc.apiusuarios.repository.IRolRepository;
import static com.jc.apiusuarios.model.Rol.RolStatus.ACTIVE;

@Service
public class IRolServiceImpl implements IRolService {
	
	@Autowired
	private IRolRepository rolRepository;

	@Autowired()
	private AutoRolMapper autoRolMapper;

	@Override
	public RolDto saveRol(RolDto rolDto) {
		return autoRolMapper.mapToRolDto(rolRepository.save(autoRolMapper.mapToRol(rolDto)));
	}

	@Override
	public RolDto updateRol(RolDto rolDto) {
		return autoRolMapper.mapToRolDto(rolRepository.save(autoRolMapper.mapToRol(rolDto)));
	}

	@Override
	public void deleteRolById(Integer id) {
		rolRepository.swapDeleteRol(Rol.RolStatus.INACTIVE, id);
	}

	@Override
	public RolDto getRolById(Integer id) {
		return autoRolMapper.mapToRolDto(rolRepository.findById(id).get());
	}


	@Override
	public List<RolDto> findAll() {
		return autoRolMapper.mapToRolDtoList(rolRepository.findAllActive(ACTIVE));
	}

	public RolDto getRolByRoleName(String roleName) {
		return autoRolMapper.mapToRolDto(rolRepository.findByRoleName(roleName));
	}
}

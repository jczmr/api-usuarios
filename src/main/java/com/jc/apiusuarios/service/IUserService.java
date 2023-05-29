package com.jc.apiusuarios.service;

import java.util.List;

import com.jc.apiusuarios.model.User;
import com.jc.apiusuarios.web.dto.UserDto;

public interface IUserService {

	List<UserDto> findAll();
	
	UserDto saveUser(UserDto userDto);

	UserDto updateUser(UserDto userDto);

	void deleteUserById(Integer id);

	UserDto getUserById(Integer id);

	UserDto findByUsername(String username);
}

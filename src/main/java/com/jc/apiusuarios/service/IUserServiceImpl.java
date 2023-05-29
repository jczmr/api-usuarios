package com.jc.apiusuarios.service;

import java.util.List;

import com.jc.apiusuarios.mapper.AutoRolMapper;
import com.jc.apiusuarios.mapper.AutoUserMapper;
import com.jc.apiusuarios.model.User;
import com.jc.apiusuarios.repository.IRolRepository;
import com.jc.apiusuarios.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jc.apiusuarios.repository.IUserRepository;
import static com.jc.apiusuarios.model.User.UserStatus.ACTIVE;

@Service
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRolRepository rolRepository;

	@Autowired
	private AutoUserMapper userMapper;

	@Autowired
	private AutoRolMapper rolMapper;
	
	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = userMapper.mapToUser(userDto);
		user.setRole(rolRepository.findById(userDto.getRole().getId()).get());
		return userMapper.mapToUserDto(userRepository.save(user));
	}

	@Override
	public UserDto updateUser(UserDto userDto) {

		return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.swapDeleteUser(User.UserStatus.INACTIVE, id);
	}

	@Override
	public UserDto getUserById(Integer id) {
		return userMapper.mapToUserDto(userRepository.findById(id).get());
	}

	@Override
	public UserDto findByUsername(String username) {
		return userMapper.mapToUserDto(userRepository.findByUsername(username));
	}

	@Override
	public List<UserDto> findAll() {
		return userMapper.mapToUserDtoList(userRepository.findAllActive(ACTIVE));
	}



}

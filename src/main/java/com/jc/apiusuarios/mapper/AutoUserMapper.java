package com.jc.apiusuarios.mapper;

import java.util.List;

import com.jc.apiusuarios.web.dto.UserDto;
import org.mapstruct.Mapper;

import com.jc.apiusuarios.model.User;

@Mapper(componentModel = "spring")
public interface AutoUserMapper {
	
	UserDto mapToUserDto(User user);
	
	List<UserDto> mapToUserDtoList(List<User> users);
	
	User mapToUser(UserDto userDto);

}

package com.samanecorp.web6ejb.mapper;

import com.samanecorp.web6ejb.dto.UserDTO;
import com.samanecorp.web6ejb.entities.UserEntity;

public class UserMapper {
	
	public static UserEntity userDtoToUserEntity(UserDTO userDTO) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(userDTO.getPassword());
		
		return userEntity;
	}
	
	public static UserDTO userEntityToUserDto(UserEntity userEntity) {
		
		UserDTO userDto = new UserDTO();
		userDto.setId(userEntity.getId());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPassword(userEntity.getPassword());
		
		return userDto;
	}

}

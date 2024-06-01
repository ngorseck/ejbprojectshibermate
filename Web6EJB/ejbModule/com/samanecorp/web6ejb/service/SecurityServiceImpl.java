package com.samanecorp.web6ejb.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.web6ejb.dao.ISecurityDao;
import com.samanecorp.web6ejb.dao.SecurityDaoImpl;
import com.samanecorp.web6ejb.dto.UserDTO;
import com.samanecorp.web6ejb.exception.EntityNotFoundException;
import com.samanecorp.web6ejb.mapper.UserMapper;

import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@LocalBean
@Stateless
public class SecurityServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@EJB
	private ISecurityDao loginDao;
	
	public Optional<UserDTO> login (String email, String password) {
		
		logger.info("Tentative email : {} pwd : {}", email, password);
		
		return loginDao.login(email, password)
					   .map(user -> {
						   	UserDTO userDto = UserMapper.userEntityToUserDto(user);
						   	logger.info("infos correct !");
						   	return Optional.of(userDto) ;
				}).orElseThrow( () -> new EntityNotFoundException("infos incorrect."));	
	}
	
	public int save (UserDTO userDTO) {
		
		return loginDao.add(UserMapper.userDtoToUserEntity(userDTO));
	}
	
}

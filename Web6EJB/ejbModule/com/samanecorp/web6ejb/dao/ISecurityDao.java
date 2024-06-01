package com.samanecorp.web6ejb.dao;

import java.util.Optional;

import com.samanecorp.web6ejb.entities.UserEntity;

import jakarta.ejb.Local;

@Local
public interface ISecurityDao extends Repository<UserEntity> {

	public Optional<UserEntity> login (String email, String pwd);
}

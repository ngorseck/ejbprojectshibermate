package com.samanecorp.web6ejb.dao;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.web6ejb.entities.UserEntity;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Stateless
public class SecurityDaoImpl extends RepositoryImpl<UserEntity> implements ISecurityDao {
	Logger logger = LoggerFactory.getLogger(SecurityDaoImpl.class);
	
	public Optional<UserEntity> login (String email, String pwd) {

		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cr.from(UserEntity.class);
		//Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), pwd);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);
		
		cr.select(user);
		cr.where(finalPredicate);
		
		return Optional.ofNullable(this.em.createQuery(cr).getSingleResult());
	}
}

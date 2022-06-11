package com.ensah.core.dao.impl;


import com.ensah.core.bo.Compte;
import com.ensah.core.dao.ICompteDaoCustom;
import org.springframework.stereotype.Repository;

@Repository
public class CompteDaoCustomImpl extends GenericJpaImpl<Compte, Long> implements ICompteDaoCustom {

	
	public Compte searchByLogin(String login) {
	
		return getEntityByColValue(Compte.class, "login", login);
	}
}

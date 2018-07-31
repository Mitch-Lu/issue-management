package com.xpo.ltl.users;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserService
{
	@PersistenceContext
	private EntityManager entityManager;


}

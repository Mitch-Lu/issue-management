package com.xpo.ltl.users;

import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import com.xpo.ltl.entity.User;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserService
{
	@PersistenceContext
	private EntityManager entityManager;

	public void add(@Valid final User user)
	{
		entityManager.persist(user);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Optional<User> get(final String userId)
	{
		final User user = entityManager.find(User.class, userId);
		return user == null ? Optional.empty() : Optional.of(user);
	}

	public boolean update(final User updateReqst)
	{
		final User user = entityManager.find(User.class, updateReqst.getUserId());
		if (user != null) {
			if (updateReqst.getEmail() != null) {
				user.setEmail(updateReqst.getEmail());
			}
			if (updateReqst.getFirstName() != null) {
				user.setFirstName(updateReqst.getFirstName());
			}
			if (updateReqst.getLastName() != null) {
				user.setLastName(updateReqst.getLastName());
			}
			if (updateReqst.getPassword() != null) {
				user.setPassword(updateReqst.getPassword());
			}
			if (updateReqst.getUsername() != null) {
				user.setUsername(updateReqst.getUsername());
			}
			entityManager.flush();
			return true;
		}
		else {
			return false;
		}
	}

	public void remove(final String userId)
	{
		final User user = entityManager.find(User.class, userId);
		entityManager.remove(user);
		entityManager.flush();
	}
}

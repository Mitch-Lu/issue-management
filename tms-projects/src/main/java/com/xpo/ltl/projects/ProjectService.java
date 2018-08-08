package com.xpo.ltl.projects;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import com.xpo.ltl.entity.Project;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProjectService
{
	@PersistenceContext
	private EntityManager entityManager;

	public void add(@Valid final Project project)
	{
		entityManager.persist(project);
		entityManager.flush();
	}
}

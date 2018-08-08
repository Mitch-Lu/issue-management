package com.xpo.ltl.tickets;

import java.util.Calendar;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.lang3.StringUtils;

import com.xpo.ltl.entity.Project;
import com.xpo.ltl.entity.Ticket;
import com.xpo.ltl.entity.User;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TicketService
{
	@PersistenceContext
	private EntityManager entityManager;

	public void add(@Valid final Ticket ticket)
	{
		if (ticket.getProject() == null || StringUtils.isBlank(ticket.getProject().getProjectId())) {
			throw new BadRequestException("Missing Project Info!");
		}
		if (ticket.getUser() == null || StringUtils.isBlank(ticket.getUser().getUserId())) {
			throw new BadRequestException("Missing Assigned User Info!");
		}

		final Project project = entityManager.find(Project.class, ticket.getProject().getProjectId());
		if (project == null) {
			throw new NotFoundException("Assigned Project NOT FOUND | ID: " + ticket.getProject().getProjectId());
		}
		final User user = entityManager.find(User.class, ticket.getUser().getUserId());
		if (user == null) {
			throw new NotFoundException("Assigned User NOT FOUND | ID: " + ticket.getUser().getUserId());
		}

		final Calendar instance = Calendar.getInstance();
		ticket.setCrteTmst(instance);
		ticket.setUpdtTmst(instance);
		ticket.setUser(user);
		ticket.setProject(project);

		entityManager.persist(ticket);
		entityManager.flush();
	}

	public Optional<Ticket> get(final long ticketId)
	{
		return entityManager
			.createNamedQuery("Ticket.findById", Ticket.class)
			.setParameter("ticketId", ticketId)
			.getResultStream()
			.findFirst();
	}
}

package com.xpo.ltl.tickets;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.xpo.ltl.dto.CommentDTO;
import com.xpo.ltl.dto.ProjectDTO;
import com.xpo.ltl.dto.TicketInfoResp;
import com.xpo.ltl.dto.UserDTO;
import com.xpo.ltl.entity.Comment;
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

	public List<TicketInfoResp> listAll()
	{
		return entityManager
			.createNamedQuery("Ticket.findAll", Ticket.class)
			.getResultStream()
			.map(ticket -> transformTicket(ticket))
			.collect(Collectors.toList());
	}

	private TicketInfoResp transformTicket(final Ticket ticket)
	{
		final TicketInfoResp resp = new TicketInfoResp();
		final Project projectEntity = ticket.getProject();

		resp.setTicketId(ticket.getTicketId());
		resp.setCrteTmst(ticket.getCrteTmst());
		resp.setDescription(ticket.getDescription());
		resp.setTicketTitle(projectEntity.getProjectId() + "-" + ticket.getTicketId());
		resp.setUpdtTmst(ticket.getUpdtTmst());

		final ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectId(projectEntity.getProjectId());
		projectDTO.setProjectName(projectEntity.getProjectName());
		projectDTO.setDescription(projectEntity.getDescription());
		resp.setProject(projectDTO);

		final UserDTO userDTO = new UserDTO();
		userDTO.setEmail(ticket.getUser().getEmail());
		userDTO.setFirstName(ticket.getUser().getFirstName());
		userDTO.setLastName(ticket.getUser().getLastName());
		userDTO.setUserId(ticket.getUser().getUserId());
		resp.setUser(userDTO);

		final List<CommentDTO> commentDTOs = ticket.getComments().stream()
			.map(commnet -> transformComment(commnet))
			.collect(Collectors.toList());
		resp.setComments(commentDTOs);

		return resp;
	}

	private CommentDTO transformComment(final Comment comment)
	{
		final CommentDTO commentDTO = new CommentDTO();
		commentDTO.setCommentId(comment.getCommentId());
		commentDTO.setCrteById(comment.getCrteById());
		commentDTO.setCrteByName(comment.getCrteByName());
		commentDTO.setCrteTmst(comment.getCrteTmst());
		commentDTO.setText(comment.getText());
		commentDTO.setUpdtTmst(comment.getUpdtTmst());

		return commentDTO;
	}

	public List<Ticket> listByUserId(final String userId)
	{
		return entityManager
			.createNamedQuery("Ticket.findByUserId", Ticket.class)
			.setParameter("userId", userId)
			.getResultList();
	}

	public long getCountByUserId(final String userId)
	{
		return entityManager
			.createNamedQuery("Ticket.findByUserId", Ticket.class)
			.setParameter("userId", userId)
			.getResultStream()
			.count();
	}
}

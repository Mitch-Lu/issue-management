package com.xpo.ltl.util;
import java.util.List;
import java.util.stream.Collectors;

import com.xpo.ltl.dto.CommentDTO;
import com.xpo.ltl.dto.ProjectDTO;
import com.xpo.ltl.dto.TicketInfoResp;
import com.xpo.ltl.dto.UserDTO;
import com.xpo.ltl.entity.Comment;
import com.xpo.ltl.entity.Project;
import com.xpo.ltl.entity.Ticket;

public final class TicketUtils
{
	public static TicketInfoResp transformTicket(final Ticket ticket)
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

	public static CommentDTO transformComment(final Comment comment)
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

}

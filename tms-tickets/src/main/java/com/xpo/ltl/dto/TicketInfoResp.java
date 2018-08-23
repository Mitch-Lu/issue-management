package com.xpo.ltl.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class TicketInfoResp implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long ticketId;
	private Calendar crteTmst;
	private String description;
	private String ticketTitle;
	private Calendar updtTmst;

	private ProjectDTO project;
	private UserDTO user;
	private List<CommentDTO> comments;

	public long getTicketId()
	{
		return ticketId;
	}

	public void setTicketId(final long ticketId)
	{
		this.ticketId = ticketId;
	}

	public Calendar getCrteTmst()
	{
		return crteTmst;
	}

	public void setCrteTmst(final Calendar crteTmst)
	{
		this.crteTmst = crteTmst;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public String getTicketTitle()
	{
		return ticketTitle;
	}

	public void setTicketTitle(final String ticketTitle)
	{
		this.ticketTitle = ticketTitle;
	}

	public Calendar getUpdtTmst()
	{
		return updtTmst;
	}

	public void setUpdtTmst(final Calendar updtTmst)
	{
		this.updtTmst = updtTmst;
	}

	public ProjectDTO getProject()
	{
		return project;
	}

	public void setProject(final ProjectDTO project)
	{
		this.project = project;
	}

	public UserDTO getUser()
	{
		return user;
	}

	public void setUser(final UserDTO user)
	{
		this.user = user;
	}

	public List<CommentDTO> getComments()
	{
		return comments;
	}

	public void setComments(final List<CommentDTO> comments)
	{
		this.comments = comments;
	}
}

package com.xpo.ltl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;


/**
 * The persistent class for the TICKETS database table.
 * 
 */
@Entity
@Table(name="TICKETS")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ticketId;
	private String assignedUser;
	private Calendar crteTmst;
	private String description;
	private String ticketTitle;
	private Calendar updtTmst;
	private Set<Comment> comments;
	private Project project;

	public Ticket() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TICKET_ID")
	public long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}


	@Column(name="ASSIGNED_USER")
	public String getAssignedUser() {
		return this.assignedUser;
	}

	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRTE_TMST")
	public Calendar getCrteTmst() {
		return this.crteTmst;
	}

	public void setCrteTmst(Calendar crteTmst) {
		this.crteTmst = crteTmst;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="TICKET_TITLE")
	public String getTicketTitle() {
		return this.ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDT_TMST")
	public Calendar getUpdtTmst() {
		return this.updtTmst;
	}

	public void setUpdtTmst(Calendar updtTmst) {
		this.updtTmst = updtTmst;
	}


	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="ticket")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setTicket(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setTicket(null);

		return comment;
	}


	//bi-directional many-to-one association to Project
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROJECT_ID")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
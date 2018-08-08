package com.xpo.ltl.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TICKETS database table.
 *
 */
@Entity
@Table(name="TICKETS")
@NamedQueries({
	@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t"),
	@NamedQuery(
		name="Ticket.findById",
		query=
			"SELECT DISTINCT t1 FROM Ticket t1 " +
			"JOIN FETCH t1.project " +
			"JOIN FETCH t1.user " +
			"LEFT JOIN FETCH t1.comments " +
			"WHERE t1.ticketId = :ticketId "
	)
})
public class Ticket implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long ticketId;
	private Calendar crteTmst;
	private String description;
	private String ticketTitle;
	private Calendar updtTmst;
	private Project project;
	private User user;
	private Set<Comment> comments = new HashSet<>();

	public Ticket() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TICKET_ID")
	public long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(final long ticketId) {
		this.ticketId = ticketId;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRTE_TMST")
	public Calendar getCrteTmst() {
		return this.crteTmst;
	}

	public void setCrteTmst(final Calendar crteTmst) {
		this.crteTmst = crteTmst;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	@Column(name="TICKET_TITLE")
	public String getTicketTitle() {
		return this.ticketTitle;
	}

	public void setTicketTitle(final String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDT_TMST")
	public Calendar getUpdtTmst() {
		return this.updtTmst;
	}

	public void setUpdtTmst(final Calendar updtTmst) {
		this.updtTmst = updtTmst;
	}


	//bi-directional many-to-one association to Project
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROJECT_ID")
	public Project getProject() {
		return this.project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}


	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSIGNED_USER")
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}


	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="ticket")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Set<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(final Comment comment) {
		getComments().add(comment);
		comment.setTicket(this);

		return comment;
	}

	public Comment removeComment(final Comment comment) {
		getComments().remove(comment);
		comment.setTicket(null);

		return comment;
	}

}
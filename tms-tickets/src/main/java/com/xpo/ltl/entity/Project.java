package com.xpo.ltl.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the PROJECTS database table.
 *
 */
@Entity
@Table(name="PROJECTS")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String projectId;
	private String description;
	private String projectName;
	private Set<Ticket> tickets = new HashSet<>();

	public Project() {
	}


	@Id
	@Column(name="PROJECT_ID")
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	@Column(name="PROJECT_NAME")
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}


	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="project")
	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(final Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(final Ticket ticket) {
		getTickets().add(ticket);
		ticket.setProject(this);

		return ticket;
	}

	public Ticket removeTicket(final Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setProject(null);

		return ticket;
	}

}
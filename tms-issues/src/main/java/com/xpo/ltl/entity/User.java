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
 * The persistent class for the USERS database table.
 *
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String username;
	private Set<Ticket> tickets = new HashSet<>();

	public User() {
	}


	@Id
	@Column(name="USER_ID")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}


	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}


	@Column(name="LAST_NAME")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}


	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="user")
	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(final Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(final Ticket ticket) {
		getTickets().add(ticket);
		ticket.setUser(this);

		return ticket;
	}

	public Ticket removeTicket(final Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setUser(null);

		return ticket;
	}

}
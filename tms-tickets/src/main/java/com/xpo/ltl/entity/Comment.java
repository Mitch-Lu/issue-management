package com.xpo.ltl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;


/**
 * The persistent class for the COMMENTS database table.
 * 
 */
@Entity
@Table(name="COMMENTS")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private long commentId;
	private String crteById;
	private String crteByName;
	private Calendar crteTmst;
	private String text;
	private Calendar updtTmst;
	private Ticket ticket;

	public Comment() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COMMENT_ID")
	public long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}


	@Column(name="CRTE_BY_ID")
	public String getCrteById() {
		return this.crteById;
	}

	public void setCrteById(String crteById) {
		this.crteById = crteById;
	}


	@Column(name="CRTE_BY_NAME")
	public String getCrteByName() {
		return this.crteByName;
	}

	public void setCrteByName(String crteByName) {
		this.crteByName = crteByName;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRTE_TMST")
	public Calendar getCrteTmst() {
		return this.crteTmst;
	}

	public void setCrteTmst(Calendar crteTmst) {
		this.crteTmst = crteTmst;
	}


	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDT_TMST")
	public Calendar getUpdtTmst() {
		return this.updtTmst;
	}

	public void setUpdtTmst(Calendar updtTmst) {
		this.updtTmst = updtTmst;
	}


	//bi-directional many-to-one association to Ticket
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TICKET_ID")
	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
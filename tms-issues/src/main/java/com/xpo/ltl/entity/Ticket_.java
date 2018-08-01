package com.xpo.ltl.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-01T16:44:58.117-0700")
@StaticMetamodel(Ticket.class)
public class Ticket_ {
	public static volatile SingularAttribute<Ticket, Long> ticketId;
	public static volatile SingularAttribute<Ticket, Calendar> crteTmst;
	public static volatile SingularAttribute<Ticket, String> description;
	public static volatile SingularAttribute<Ticket, String> ticketTitle;
	public static volatile SingularAttribute<Ticket, Calendar> updtTmst;
	public static volatile SingularAttribute<Ticket, Project> project;
	public static volatile SingularAttribute<Ticket, User> user;
	public static volatile SetAttribute<Ticket, Comment> comments;
}

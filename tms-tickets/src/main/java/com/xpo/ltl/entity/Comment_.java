package com.xpo.ltl.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-25T13:51:15.888-0700")
@StaticMetamodel(Comment.class)
public class Comment_ {
	public static volatile SingularAttribute<Comment, Long> commentId;
	public static volatile SingularAttribute<Comment, String> crteById;
	public static volatile SingularAttribute<Comment, String> crteByName;
	public static volatile SingularAttribute<Comment, Calendar> crteTmst;
	public static volatile SingularAttribute<Comment, Calendar> updtTmst;
	public static volatile SingularAttribute<Comment, Ticket> ticket;
	public static volatile SingularAttribute<Comment, String> text;
}

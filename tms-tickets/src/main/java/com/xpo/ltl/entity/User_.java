package com.xpo.ltl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-30T11:41:31.144-0700")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> userId;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SetAttribute<User, Ticket> tickets;
}

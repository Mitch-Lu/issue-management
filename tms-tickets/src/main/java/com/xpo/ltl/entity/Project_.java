package com.xpo.ltl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-25T13:51:15.916-0700")
@StaticMetamodel(Project.class)
public class Project_ {
	public static volatile SingularAttribute<Project, String> projectId;
	public static volatile SingularAttribute<Project, String> projectName;
	public static volatile SetAttribute<Project, Ticket> tickets;
	public static volatile SingularAttribute<Project, String> description;
}

package com.xpo.ltl.dto;

import java.io.Serializable;

public class ProjectDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String projectId;
	private String description;
	private String projectName;

	public String getProjectId()
	{
		return projectId;
	}

	public void setProjectId(final String projectId)
	{
		this.projectId = projectId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(final String projectName)
	{
		this.projectName = projectName;
	}

}

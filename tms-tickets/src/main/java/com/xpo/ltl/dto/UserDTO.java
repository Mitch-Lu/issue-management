package com.xpo.ltl.dto;

import java.io.Serializable;

public class UserDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String userId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String username;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(final String userId)
	{
		this.userId = userId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

}

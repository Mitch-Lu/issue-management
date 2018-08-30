package com.xpo.ltl.security.dto;

import java.util.Set;

public class AuthenticatedUser
{
	private String userId;
	private Set<String> scopes;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(final String userId)
	{
		this.userId = userId;
	}

	public Set<String> getScopes()
	{
		return scopes;
	}

	public void setScopes(final Set<String> scopes)
	{
		this.scopes = scopes;
	}
}

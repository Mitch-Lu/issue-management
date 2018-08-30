package com.xpo.ltl.security.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

import com.xpo.ltl.security.dto.AuthenticatedUser;

@RequestScoped
public class AuthenticatedUserProducer
{
	@RequestScoped
	@Produces
	private AuthenticatedUser user;

	public void observeAuthenticationEvent(@Observes final AuthenticatedUser user)
	{
		this.user = user;
	}
}

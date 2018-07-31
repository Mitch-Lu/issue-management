package com.xpo.ltl.users;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
public class TMSApplication extends Application
{
	@Override
	public Set<Class<?>> getClasses()
	{
		final Set<Class<?>> resources = new HashSet<>();
		resources.add(UserResource.class);
		return resources;
	}
}

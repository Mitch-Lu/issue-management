package com.xpo.ltl.dto;

import com.xpo.ltl.constant.EventTypeEnum;

public class TicketEvent
{
	private final EventTypeEnum evtType;
	private final String userId;

	public TicketEvent(final EventTypeEnum evtType, final String userId)
	{
		this.evtType = evtType;
		this.userId = userId;
	}

	public EventTypeEnum getEvtType()
	{
		return evtType;
	}

	public String getUserId()
	{
		return userId;
	}
}

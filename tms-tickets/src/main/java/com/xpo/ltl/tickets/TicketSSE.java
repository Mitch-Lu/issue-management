package com.xpo.ltl.tickets;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import com.xpo.ltl.dto.TicketEvent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Singleton
//@Startup
@Path("/activity")
@Api(value = "activity")
public class TicketSSE
{
	private SseBroadcaster broadcaster;
	private Sse sse;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @ApiOperation(value = "SSE Subscribtion")
    public void subscribe(@Context final Sse sse, @Context final SseEventSink eventSink)
    {
        this.sse = sse;
        if(this.broadcaster == null) {
            this.broadcaster = sse.newBroadcaster();
        }
        this.broadcaster.register(eventSink);
    }

    public void observeEvent(final TicketEvent event)
    {
        if(this.broadcaster != null) {
            final String jsonEvt = JsonbBuilder.create().toJson(event);
        	this.broadcaster.broadcast(this.sse.newEvent(jsonEvt));
        }
    }

    @PreDestroy
    public void close()
    {
        if(this.broadcaster != null) {
            this.broadcaster.close();
        }
    }
}

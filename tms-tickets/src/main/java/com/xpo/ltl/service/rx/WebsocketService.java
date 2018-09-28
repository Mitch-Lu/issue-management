package com.xpo.ltl.service.rx;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.json.bind.JsonbBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.xpo.ltl.dto.TicketEvent;

@Singleton
@Startup
@ServerEndpoint(value = "/feed")
public class WebsocketService
{
 	/* All open WebSocket sessions */
	private static final Set<Session> SESSIONS = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void openConnection(
		final Session session)
	{
		//session.getUserProperties().put(loginUserId, true);
		SESSIONS.add(session);
	}

    public void observeEvent(@Observes final TicketEvent event)
    {
    	SESSIONS.stream().filter(Session::isOpen)
    		.forEach(session -> {
    			final String sse = JsonbBuilder.create().toJson(event);
    			try {
					session.getBasicRemote().sendText(sse);
				}
				catch (final IOException e) {
					e.printStackTrace();
				}
    		});
    }

	@OnClose
	public void closeConnection(final Session session)
	{
		SESSIONS.remove(session);
	}

    @OnError
    public void onError(final Session session, final Throwable error)
    {
        System.out.println("Throwable message: " + error.getMessage());
        try {
            if (session != null) {
                session.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}

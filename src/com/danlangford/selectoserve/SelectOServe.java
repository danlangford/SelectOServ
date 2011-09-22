package com.danlangford.selectoserve;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

public class SelectOServe {

	public static void main(String[] args) throws Exception {
		Server checkIn = new Server();
		
		SelectChannelConnector ciConn = new SelectChannelConnector();
		ciConn.setPort(8181);
		checkIn.addConnector(ciConn);

		ResourceHandler ciResHand = new ResourceHandler();
		ciResHand.setWelcomeFiles(new String[] { "index.html" });

		ciResHand.setResourceBase("./web/checkIn");

		HandlerList ciHands = new HandlerList();
		ciHands.setHandlers(new Handler[] { ciResHand,
				new DefaultHandler() });
		checkIn.setHandler(ciHands);

		checkIn.start();
		//checkIn.join();
		
		Server admin = new Server();
		
		SelectChannelConnector admConn = new SelectChannelConnector();
		admConn.setHost("127.0.0.1");
		admConn.setPort(8080);
		admin.addConnector(admConn);

		ResourceHandler admResHand = new ResourceHandler();
		admResHand.setWelcomeFiles(new String[] { "index.html" });

		admResHand.setResourceBase("./web/admin");

		HandlerList admHands = new HandlerList();
		admHands.setHandlers(new Handler[] { admResHand,
				new DefaultHandler() });
		admin.setHandler(admHands);

		admin.start();
		//admin.join();
	}

}

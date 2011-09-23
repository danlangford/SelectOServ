package com.danlangford.selectoserve;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class SelectOServe {

	public static void main(String[] args) throws Exception {
		
		for(String a : args) {
			System.out.println(a);
		}
		
		MakeShiftDataStore myDataStore = MakeShiftDataStore.getInstance();

		Server checkIn = new Server();
		
		SelectChannelConnector ciConn = new SelectChannelConnector();
		ciConn.setPort(8181);
		checkIn.addConnector(ciConn);
		
		String ciWebDir = SelectOServe.class.getClassLoader().getResource("com/danlangford/selectoserve/files/checkIn/").toExternalForm();
		ResourceHandler ciStaticFiles = new ResourceHandler();
		ciStaticFiles.setWelcomeFiles(new String[] { "index.html" });
		ciStaticFiles.setResourceBase(ciWebDir); // <- file system

		ServletContextHandler ciServeHand = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		//ciServeHand.setContextPath("/");
		CheckInServlet ciServlet = new CheckInServlet(myDataStore);
		ciServeHand.addServlet(new ServletHolder(ciServlet), "/checkIn"); // <- url

		HandlerList ciHandlers = new HandlerList();
		ciHandlers.setHandlers(new Handler[] { ciStaticFiles, ciServeHand,
				new DefaultHandler() });
		checkIn.setHandler(ciHandlers);

		checkIn.start();

		// /////////
		// ////////
		// ///////

		Server admin = new Server();

		SelectChannelConnector admConn = new SelectChannelConnector();
		admConn.setHost("127.0.0.1");
		admConn.setPort(8080);
		admin.addConnector(admConn);

		String admWebDir = SelectOServe.class.getClassLoader().getResource("com/danlangford/selectoserve/files/admin/").toExternalForm();
		ResourceHandler admResHand = new ResourceHandler();
		admResHand.setWelcomeFiles(new String[] { "index.html" });
		admResHand.setResourceBase(admWebDir); // <- file system
		
		ServletContextHandler admServeHand = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		//admServeHand.setContextPath("/");
		HostsServlet admServlet = new HostsServlet();
		admServeHand.addServlet(new ServletHolder(admServlet), "/hosts"); // <- url
		ListDataServlet admList = new ListDataServlet(myDataStore);
		admServeHand.addServlet(new ServletHolder(admList), "/list"); // <- url

		HandlerList admHands = new HandlerList();
		admHands.setHandlers(new Handler[] { admResHand, admServeHand, new DefaultHandler() });
		admin.setHandler(admHands);

		admin.start();
		// admin.join();
	}

}

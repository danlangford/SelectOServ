package com.danlangford.selectoserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HostsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HostsServlet() { }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO this servlet actually needs to
		// write the selected ip to the hosts file
		String ip = request.getParameter("ip");
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>" + "? hosts greeting ?" + "</h1>");
		
		String msg = "// TODO write to hosts file and redirect <br/> ip=" + ip;
		response.getWriter().println(msg);
		System.out.println(msg);
	}

}

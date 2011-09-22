package com.danlangford.selectoserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckInServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MakeShiftDataStore dataStore;

	public CheckInServlet(MakeShiftDataStore dataStore) {
		this.dataStore = dataStore;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ip = request.getParameter("ip");
		String name = request.getParameter("name");
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>" + "checked in" + "</h1>");

		if (ip != null && name != null) {
			dataStore.addServer(new ServingComp(ip, name));
			response.getWriter().println(
					"wrote to datastore <br/> ip=" + ip + " name=" + name);
		} else {
			response.getWriter().println("<b>sorry i got some <i>NULL</i> data</b>");
		}

	}

}

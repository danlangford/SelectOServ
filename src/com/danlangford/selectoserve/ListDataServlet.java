package com.danlangford.selectoserve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MakeShiftDataStore dataStore;

	public ListDataServlet(MakeShiftDataStore dataStore) {
		this.dataStore = dataStore;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print("{\"list\":[");
		
		List<ServingComp> data = dataStore.getServers();
		
		String json = "";
		for(ServingComp sc : data) {
			json += "{\"name\":\""+sc.getName()+"\",\"ip\":\""+sc.getIp()+"\",\"time\":\""+sc.getTime().toString()+"\"},";
		}
		
		if(json.length()>1) {
			json = json.substring(0,json.length()-1 );
		}
		
		response.getWriter().print(json+"]}");
	}

}

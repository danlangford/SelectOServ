package com.danlangford.selectoserve;

import java.util.ArrayList;

public class MakeShiftDataStore {
	
	private static MakeShiftDataStore instance = new MakeShiftDataStore();
	
	public static MakeShiftDataStore getInstance() {
		if(instance==null) {
			instance=new MakeShiftDataStore();
		}
		return instance;
	}
	
	private ArrayList<ServingComp> servers = new ArrayList<ServingComp>();
	
	private MakeShiftDataStore() {
		
	}
	
	public void addServer(ServingComp server) {
		// check any input if you needs to
		servers.add(server);
	}
	
	public ArrayList<ServingComp> getServers() {
		// do any default sorting or whatever
		return servers;
	}

}

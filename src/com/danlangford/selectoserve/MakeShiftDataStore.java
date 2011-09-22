package com.danlangford.selectoserve;

import java.util.HashMap;

public class MakeShiftDataStore {

	private static MakeShiftDataStore instance = new MakeShiftDataStore();

	public static MakeShiftDataStore getInstance() {
		if (instance == null) {
			instance = new MakeShiftDataStore();
		}
		return instance;
	}

	private HashMap<String, ServingComp> servers = new HashMap<String, ServingComp>();

	private MakeShiftDataStore() {

	}

	public void addServer(ServingComp server) {
		// check any input if you needs to
		servers.put(server.getIp(), server);
	}

	public HashMap<String, ServingComp> getServers() {
		// do any default sorting or whatever
		return servers;
	}

}

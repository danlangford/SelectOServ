package com.danlangford.selectoserve;

public class ServingComp {
	
	public ServingComp(String ip, String id) {
		this.setIpAddress(ip);
		this.setIdentifier(id);
	}
	
	private String ipAddress;
	private String identifier;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}

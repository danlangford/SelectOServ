package com.danlangford.selectoserve;

import java.util.Date;

public class ServingComp {

	private String ip;
	private String name;
	private Date time;

	public ServingComp(String ip, String name) {
		this.ip = ip;
		this.name = name;
		this.time = new Date();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	

}

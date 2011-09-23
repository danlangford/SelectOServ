package com.danlangford.selectoserve;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class HostEditor {

	private static final String NL = System.getProperty("line.separator");
	
	public void update(String hostPath, String user, String address) {
		if( nullOrEmpty(hostPath, user, address) ) throw new RuntimeException("All parameters are required"); 
		
		File host = new File(hostPath);
		if( !host.exists() ) throw new RuntimeException("Host File Doesn't exist: " + hostPath);
		if( !host.canRead() || !host.canWrite() ) throw new RuntimeException("Host file must allow read/write");
		
		try {
			File newHost = File.createTempFile("tmd", "hostfile");
			BufferedWriter out = new BufferedWriter(new FileWriter(newHost));
			
			boolean found = false;
			
			Scanner scanner = new Scanner(host);
			while(scanner.hasNext()) {
				found |= onReadLine(scanner.nextLine(), out, user, address);
			}
			
			if(!found) {
				out.write(address + "\t" + user + "\t#" + user + NL);
			}
			
			out.flush();
			out.close();
			
			scanner.close();
			newHost.renameTo(host);
		} catch (IOException e) {
			throw new RuntimeException("I/O error", e);
		}
	}
	
	private boolean onReadLine(String line, BufferedWriter out, String user, String address) throws IOException {
		String[] inputs = line.split("\\s+");
		
		// handle a line with no words
		boolean skipLine = inputs.length < 3;
		// handle comment lines
		skipLine = skipLine || inputs[0].trim().isEmpty() || inputs[0].trim().charAt(0) == '#';		
		
		// at this point, 0 = ip, 1 = name, 2 = username comment
		// if it's not our user, don't touch it
		skipLine = skipLine || !("#" + user).equalsIgnoreCase(inputs[2]);
		
		if(skipLine) {
			out.write(line + NL);
			return false;
		}
		
		out.write(address + "\t" + user + "\t#" + user + NL);
		return true;
	}
	
	private boolean nullOrEmpty(String... values) {
		for(String value : values) {
			if( value == null || value.trim().isEmpty() ) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception{
		HostEditor editor = new HostEditor();
		editor.update("/etc/hosts", "andre", "192.168.1.100");
	}
}

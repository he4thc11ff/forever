package com.effective.section3.subject;

public class Monitor implements Comparable<Monitor>{

	private String size;
	private String resolution;
	private String qualified;
	
	public String getQualified() {
		return qualified;
	}

	public void setQualified(String qualified) {
		this.qualified = qualified;
	}
	
	public Monitor() {
		super();
		
	}
	
	public Monitor(String size, String resolution, String qualified) {
		
		this.size = size;
		this.resolution = resolution;
		this.qualified = qualified;
	}

	public String toString(){
		return size+"\t\t"+resolution+"\t"+qualified;
	}
	

	@Override
	public int compareTo(Monitor otherMonitor) {
		
		if(!this.qualified.equals(otherMonitor.qualified))
			return -1*(this.qualified.compareTo(otherMonitor.qualified));
		else if(!this.size.equals(otherMonitor.size))
			return -1*(this.size.compareTo(otherMonitor.size));
		else if(!this.resolution.equals(otherMonitor))
			return this.resolution.compareTo(otherMonitor.resolution);
		
		
		return 0;
	}
	
	

}


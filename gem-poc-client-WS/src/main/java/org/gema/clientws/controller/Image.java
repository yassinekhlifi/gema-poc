package org.gema.clientws.controller;

public class Image {
	int id;
	String title;
	String url;
	String port;
	
	
	
	public Image (int id, String title, String url,String port) {
		
		super();
		this.id=id;
		this.title=title;
		this.url=url;
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

		public String getUrl() {
		return url;
	}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

	

	
}

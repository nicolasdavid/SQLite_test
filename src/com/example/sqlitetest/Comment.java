package com.example.sqlitetest;

public class Comment {
	private long id;
	private String description;
	
	public long getId(){
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String str) {
		this.description = str;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	//will be used by the ArayAdapter in the ListView
	@Override
	public String toString() {
		return "id =" + id + "&" + "\n description =" + this.description ;
	}
	
	
		
}

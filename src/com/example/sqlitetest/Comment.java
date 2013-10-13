package com.example.testdatabaseactivity;

public class Comment {
	private long id;
	private String comment;
	
	public long getId(){
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	//will be used by the ArayAdapter in the ListView
	@Override
	public String toString() {
		return "Comment [comment=" + comment + "]";
	}
	
	
		
}

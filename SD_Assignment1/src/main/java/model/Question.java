package model;

public class Question {

	private int id;
	private String title;
	private String body;
	private String tags;
	
	
	public Question(int id, String title, String body, String tags) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.tags = tags;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public boolean containsTag(String tag) {
		return tags.contains(tag);
	}
}

package ro.utcn.aron.model;

import java.util.ArrayList;
import java.util.List;

public class Question implements Comparable {

	private int id;
	private String title;
	private String body;
	private String tags;
	private String author;
	private String creationDate;
	private List<Answer> answers = new ArrayList<>();
	
	public Question(int id, String title, String body, String tags, String author, String creationDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.tags = tags;
		this.author = author;
		this.creationDate = creationDate;
	}
	
	public Question(int id, String title, String body, String tags, String author, String creationDate, List<Answer> answers) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.tags = tags;
		this.author = author;
		this.creationDate = creationDate;
		this.answers = answers;
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
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}


	public void addAnswer(Answer answer) {
		answers.add(answer);
	}
	
	public boolean containsTag(String tag) {
		return tags.contains(tag);
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		String txt = id+"\n";
		txt += title+"\n";
		txt += body+"\n";
		txt += author+"\n";
		txt += creationDate.toString()+"\n";
		txt += tags;
		txt += "\n";
		
		if(answers.size() > 0) {
			txt += "\nAnswers:\n";
			for(Answer a: answers) {
				txt += a.toString();
			}
			txt += "\n";
		}
		return txt;
	}


	@Override
	public int compareTo(Object arg0) {
		Question q2 = (Question)arg0;
		
		if(id > q2.getId()) return 1;
		if(id < q2.getId()) return -1;
		return 0;
	}
	
	
}

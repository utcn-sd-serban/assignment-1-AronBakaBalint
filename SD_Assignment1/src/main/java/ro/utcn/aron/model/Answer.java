package ro.utcn.aron.model;

public class Answer {

	private int id;
	private String text;
	private String author;
	private String creationDate;
	
	public Answer(String text, String author, String creationDate) {
		this.text = text;
		this.author = author;
		this.creationDate = creationDate;
	}
	
	public Answer(int id, String text, String author, String creationDate) {
		this.id = id;
		this.text = text;
		this.author = author;
		this.creationDate = creationDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String txt = "";
		txt += id+"\n";
		txt += text+"\n";
		txt += author+"\n";
		txt += creationDate+"\n";
		
		return txt;
	}
	
}

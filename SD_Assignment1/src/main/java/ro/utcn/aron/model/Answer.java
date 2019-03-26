package ro.utcn.aron.model;

public class Answer {

	private String text;
	private String author;
	private String creationDate;
	
	public Answer(String text, String author, String creationDate) {
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
	
	@Override
	public String toString() {
		String txt = "";
		txt += text+"\n";
		txt += author+"\n";
		txt += creationDate+"\n";
		
		txt += "\n";
		return txt;
	}
	
}

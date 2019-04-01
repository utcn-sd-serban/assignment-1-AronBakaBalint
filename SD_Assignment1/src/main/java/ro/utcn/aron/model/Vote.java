package ro.utcn.aron.model;

public class Vote {

	private String author;
	int id; //question or answer id
	int voteYype;
	
	
	public Vote(String author, int id, int voteYype) {
		super();
		this.author = author;
		this.id = id;
		this.voteYype = voteYype;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVoteYype() {
		return voteYype;
	}
	public void setVoteYype(int voteYype) {
		this.voteYype = voteYype;
	}
	
	
}

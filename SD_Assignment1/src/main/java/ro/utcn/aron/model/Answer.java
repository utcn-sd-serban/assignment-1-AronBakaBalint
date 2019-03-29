package ro.utcn.aron.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Answer implements Comparable {

	private int id;
	private int score = 0;
	private String text;
	private String author;
	private String creationDate;
	
	//user and +1 for upvote -1 for downvote
	private Map<String, Integer> votes = new ConcurrentHashMap<>();
	
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
		txt += score + "  " + text+"\n";
		txt += author+"\n";
		txt += creationDate+"\n";
		
		return txt;
	}
	
	public void upVote(String user) {
		if(user.equals(author)) {
			System.out.println("You cannot vote your own anser!");
			return;
		}
		
		if(votes.containsKey(user) && votes.get(user) == 1) {
			System.out.println("You already upvoted this answer!");
			return;
		}
		
		//if we change vote from -1 to +1 then 2 has to be added
		if(votes.containsKey(user)) score++;
		score++;
		votes.put(user,1);
		
	}
	
	public void downVote(String user) {
		if(user.equals(author)) {
			System.out.println("You cannot vote your own anser!");
			return;
		}
		
		if(votes.containsKey(user) && votes.get(user) == -1) {
			System.out.println("You already downvoted this answer!");
			return;
		}
		
		//if we change vote from +1 to -1 then 2 has to be subtracted
		if(votes.containsKey(user)) score--;
		score--;
		votes.put(user,-1);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Object arg0) {
		Answer answer2 = (Answer)arg0;
		
		if(answer2.getScore() > score) return 1;
		if(answer2.getScore() < score) return -1;
		return 0;
		
	}	
	
	
}

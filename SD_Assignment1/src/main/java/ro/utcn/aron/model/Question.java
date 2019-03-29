package ro.utcn.aron.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Question implements Comparable {

	private int id;
	private int score = 0;
	private String title;
	private String body;
	private String tags;
	private String author;
	private String creationDate;
	private List<Answer> answers = new ArrayList<>();
	
	//user and +1 for upvote -1 for downvote
	private Map<String, Integer> votes = new ConcurrentHashMap<>();
	
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
	
	public void editAnswer(int answerid, String user, String newText) {
		answers.forEach(answer->{
			if(answer.getId() == answerid) {
				if(answer.getAuthor().equals(user)) {
					answer.setText(newText);
				} else {
					System.out.println("You can edit only your answer!");
				}
			}
		});
	}
	
	public  void removeAnswer(int answerid, String user) {
		for(int i=0;i < answers.size(); i++) {
			if(answers.get(i).getId() == answerid) {
				if(answers.get(i).getAuthor().equals(user)) {
					answers.remove(answers.get(i));
					return;
				} else {
					System.out.println("You can delete only your answer!");
				}
			}
		};
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
		txt += score + "  " + title+"\n";
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
	

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void upVote(String user) {
		if(user.equals(author)) {
			System.out.println("You cannot vote your own question!");
			return;
		}
		
		if(votes.containsKey(user) && votes.get(user) == 1) {
			System.out.println("You already upvoted this question!");
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
	
	public void upVoteAnswer(String user, int answerid) {
		answers.forEach(answer->{
			if(answer.getId() == answerid)
				answer.upVote(user);
		});
		
		//sort answers based on their scores
		Collections.sort(answers);
	}
	
	public void downVoteAnswer(String user, int answerid) {
		answers.forEach(answer->{
			if(answer.getId() == answerid)
				answer.downVote(user);
		});
		
		//sort answers based on their scores
		Collections.sort(answers);
	}
	
}

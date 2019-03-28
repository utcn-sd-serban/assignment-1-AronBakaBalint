package ro.utcn.aron.persistence.api;

import java.util.List;
import java.util.Optional;

import ro.utcn.aron.model.Question;

public interface QuestionRepository {

	Question save(Question question);
	
	Optional<Question> findById(int id);
	
	void editQuestion(int questionid, String user, String text);
	
	void remove(Question question);
	
	void answerQuestion(int questionid, String user, String answer);
	
	void removeAnswer(int answerid, String user);
	
	void editAnswer(int answerid, String user, String newText);
	
	List<Question> findAll();
	
	List<Question> filterByTitle(String title);
	
	List<Question> filterByTag(String tag);
}

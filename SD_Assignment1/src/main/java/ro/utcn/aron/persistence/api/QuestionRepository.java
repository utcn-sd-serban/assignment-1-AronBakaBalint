package ro.utcn.aron.persistence.api;

import java.util.List;
import java.util.Optional;

import ro.utcn.aron.model.Question;

public interface QuestionRepository {

	Question save(Question question);
	
	Optional<Question> findById(int id);
	
	void remove(Question question);
	
	void answerQuestion(int questionid, String user, String answer);
	
	List<Question> findAll();
	
	List<Question> filterByTitle(String title);
	
	List<Question> filterByTag(String tag);
}

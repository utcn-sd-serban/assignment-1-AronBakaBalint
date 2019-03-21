package ro.utcn.aron.api;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ro.utcn.aron.model.Question;

@Component
public interface QuestionRepository {

	Question save(Question question);
	
	Optional<Question> findByID(int id);
	
	void remove(Question question);
	
	List<Question> findAll();
	
	Optional<List<Question>> filterByTitle(String title);
	
	Optional<List<Question>> filterByTag(String tag);
}

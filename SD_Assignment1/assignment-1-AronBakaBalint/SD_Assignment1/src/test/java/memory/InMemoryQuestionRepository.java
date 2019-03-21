package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import api.QuestionRepository;
import model.Question;

public class InMemoryQuestionRepository implements QuestionRepository {

	private int currentId = 1;
	private Map<Integer, Question> data = new HashMap<>();
	
	@Override
	public synchronized Question save(Question question) {
		if(question.getId() != 0) {
			data.put(question.getId(), question);
		} else {
			question.setId(currentId++);
			data.put(question.getId(), question);
		}
		return question;
	}

	@Override
	public Optional<Question> findByID(int id) {
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public synchronized void remove(Question question) {
		data.remove(question.getId());
		
	}

	@Override
	public List<Question> findAll() {
		return new ArrayList<>(data.values());
	}

	
}

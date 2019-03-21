package ro.utcn.aron.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import ro.utcn.aron.api.QuestionRepository;
import ro.utcn.aron.model.Question;

@Component
@ConditionalOnProperty(name = "aron.repository-type", havingValue = "MEMORY")
public class InMemoryQuestionRepository implements QuestionRepository {

	private AtomicInteger currentId = new AtomicInteger(0);
	private Map<Integer, Question> data = new ConcurrentHashMap<>();
	
	@Override
	public synchronized Question save(Question question) {
		if(question.getId() != 0) {
			data.put(question.getId(), question);
		} else {
			question.setId(currentId.get());
			currentId.set(currentId.get()+1);
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

	@Override
	public Optional<List<Question>> filterByTitle(String title) {
		List<Question> result = new ArrayList<>();
		data.forEach((K, V)->{
			if(title.contains(V.getTags())) {
				result.add(V);
			}
		});
		
		return Optional.ofNullable(result);
	}

	@Override
	public Optional<List<Question>> filterByTag(String tag) {
		List<Question> result = new ArrayList<>();
		data.forEach((K, V)->{
			if(V.containsTag(tag)) {
				result.add(V);
			}
		});
		
		return Optional.ofNullable(result);
	}

	
}

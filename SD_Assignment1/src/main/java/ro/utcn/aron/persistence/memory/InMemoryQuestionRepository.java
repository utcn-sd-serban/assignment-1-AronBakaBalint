package ro.utcn.aron.persistence.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ro.utcn.aron.model.Answer;
import ro.utcn.aron.model.Question;
import ro.utcn.aron.persistence.api.QuestionRepository;

@Component
public class InMemoryQuestionRepository implements QuestionRepository {

	private static final Map <Integer, Question> data = new ConcurrentHashMap<>();
	private static AtomicInteger currentId = new AtomicInteger(0);
	
	
	@Override
	public synchronized Question save(Question question) {
		if(question.getId() != 0) {
			data.put(question.getId(), question);
		} else {
			currentId.getAndIncrement();
			question.setId(currentId.intValue());
			data.put(currentId.intValue(), question);
		}
		
		return question;
	}

	@Override
	public Optional<Question> findById(int id) {
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public synchronized void remove(Question question) {
		data.remove(question.getId());
	}

	@Override
	public List<Question> findAll() {
		ArrayList<Question> questionList = new ArrayList<>(data.values());
		Collections.sort(questionList);
		
		return questionList;
	}

	@Override
	public List<Question> filterByTitle(String title) {
		ArrayList<Question> questionList = new ArrayList<>(data.values());
		return questionList.stream().filter(q->q.getTitle().contains(title)).collect(Collectors.toList());
	}

	@Override
	public List<Question> filterByTag(String text) {
		ArrayList<Question> questionList = new ArrayList<>(data.values());
		return questionList.stream().filter(q->q.getTags().contains(text)).collect(Collectors.toList());
	}

	@Override
	public void answerQuestion(int questionid, String user, String answer) {
		data.get(questionid).addAnswer(new Answer(answer, user, new Date().toString()));	
	}

	

}

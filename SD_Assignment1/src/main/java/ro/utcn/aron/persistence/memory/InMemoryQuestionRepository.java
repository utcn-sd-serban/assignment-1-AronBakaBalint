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

	private  final Map <Integer, Question> data = new ConcurrentHashMap<>();
	private  AtomicInteger currentQuestionId = new AtomicInteger(0);
	private  AtomicInteger currentAnswerId = new AtomicInteger(0);
	
	
	@Override
	public synchronized Question save(Question question) {
		if(question.getId() != 0) {
			data.put(question.getId(), question);
		} else {
			currentQuestionId.getAndIncrement();
			question.setId(currentQuestionId.intValue());
			data.put(currentQuestionId.intValue(), question);
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
	public void answerQuestion(int questionid, String user, String answerText) {
		Answer answer = new Answer(answerText, user, new Date().toString());
		currentAnswerId.getAndIncrement();
		answer.setId(currentAnswerId.intValue());
		data.get(questionid).addAnswer(answer);	
	}

	@Override
	public void editQuestion(int questionid, String user, String text) {
		if(!data.get(questionid).getAuthor().equals(user)) {
			System.out.println("You can edit only your question!");
			return;
		}
		
		data.get(questionid).setBody(text);
		
	}

	@Override
	public synchronized void removeAnswer(int answerid, String user) {
		data.forEach((K, V)->{
			V.removeAnswer(answerid, user);
		});
		
	}

	@Override
	public void editAnswer(int answerid, String user, String newText) {
		data.forEach((K, V)->{
			V.editAnswer(answerid, user, newText);
		});
		
	}

	@Override
	public void upVoteAnswer(String username, int answerid) {
		data.values().forEach(question->question.upVoteAnswer(username, answerid));
		
	}

	@Override
	public void downVoteAnswer(String username, int answerid) {
		data.values().forEach(question->question.downVoteAnswer(username, answerid));
		
	}

	@Override
	public void upVoteQuestion(String username, int questionid) {
		data.get(questionid).upVote(username);
		
	}

	@Override
	public void downVoteQuestion(String username, int answerid) {
		data.get(answerid).downVote(username);
		
	}
		

}

package ro.utcn.aron.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ro.utcn.aron.mode.RepositoryMode;
import ro.utcn.aron.model.Question;
import ro.utcn.aron.persistence.api.QuestionRepository;
import ro.utcn.aron.persistence.api.QuestionRepositoryFactory;
import ro.utcn.aron.persistence.jdbc.JdbcQuestionRepositoryFactory;
import ro.utcn.aron.persistence.memory.InMemoryQuestionRepositoryFactory;

public class QuestionManagementService {

	private final QuestionRepositoryFactory questionRepositoryFactory;
	
	public QuestionManagementService() {
		
		if(RepositoryMode.repoMode.equals("JDBC"))
			questionRepositoryFactory = new JdbcQuestionRepositoryFactory();
		else
			questionRepositoryFactory = new InMemoryQuestionRepositoryFactory();
	}
	
	@Transactional
	public List<Question> listQuestions() {
		return questionRepositoryFactory.createQuestionRepository().findAll();
	}
	
	@Transactional
	public Question addQuestion(String title, String body, String tags, String author) {
		return questionRepositoryFactory.createQuestionRepository().save(new Question(0, title, body, tags, author, new Date().toString()));
	}
	
	@Transactional
	public void removeQuestion(int id) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		Question question = repository.findById(id).orElseThrow(RuntimeException::new);
		repository.remove(question);
	}
	
	@Transactional
	public List<Question> filterByTitle(String title){
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		return repository.filterByTitle(title);
	}
	
	@Transactional
	public List<Question> filterByTag(String tag){
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		return repository.filterByTag(tag);
	}
	
	@Transactional
	public void answerQuestion(String user, int questionid, String answer) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.answerQuestion(questionid, user, answer);
	}
}

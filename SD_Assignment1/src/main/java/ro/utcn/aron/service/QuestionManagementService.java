package ro.utcn.aron.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ro.utcn.aron.model.Question;
import ro.utcn.aron.persistence.api.QuestionRepository;
import ro.utcn.aron.persistence.api.RepositoryFactory;

@Component
public class QuestionManagementService {

	private final RepositoryFactory questionRepositoryFactory;
	
	public QuestionManagementService(RepositoryFactory questionRepositoryFactory) {
		
		this.questionRepositoryFactory = questionRepositoryFactory;
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
	
	@Transactional
	public void editQuestion(String user, int questionid, String answer) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.editQuestion(questionid, user, answer);
	}
	
	@Transactional
	public void editAnswer(String user, int answerid, String answer) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.editAnswer(answerid, user, answer);
	}
	
	@Transactional
	public void deleteAnswer(String user, int answerid) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.removeAnswer(answerid, user);
	}
	
	@Transactional
	public void upVoteAnswer(String user, int answerid) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.upVoteAnswer(user, answerid);
	}
	
	@Transactional
	public void downVoteAnswer(String user, int answerid) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.downVoteAnswer(user, answerid);
	}
	
	@Transactional
	public void upVoteQuestion(String user, int questionid) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.upVoteQuestion(user, questionid);
	}
	
	@Transactional
	public void downVoteQuestion(String user, int questionid) {
		QuestionRepository repository = questionRepositoryFactory.createQuestionRepository();
		repository.downVoteQuestion(user, questionid);
	}
}

package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import api.QuestionRepository;
import api.RepositoryFactory;
import model.Question;

public class QuestionManagementService {

	private final RepositoryFactory repositoryFactory;
	
		public QuestionManagementService(RepositoryFactory repositoryFactory) {
			this.repositoryFactory = repositoryFactory;
		}
		
	@Transactional
	public List<Question> listQuestions(){
		return repositoryFactory.createQuestionRepository().findAll();
	}
	
	@Transactional
	public void removeQuestion(int id) {
		QuestionRepository repository = repositoryFactory.createQuestionRepository();
		Question question = repository.findByID(id).orElseThrow(RuntimeException::new);
		repository.remove(question);
	}
	
	@Transactional
	public Question save(String title, String text, String tags) {
		QuestionRepository repository = repositoryFactory.createQuestionRepository();
		Question question = repository.save(new Question(0, title, text, tags));
		return question;
	}
		
	
}

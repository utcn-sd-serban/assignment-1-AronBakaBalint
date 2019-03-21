package ro.utcn.aron.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ro.utcn.aron.api.QuestionRepository;
import ro.utcn.aron.api.RepositoryFactory;
import ro.utcn.aron.model.Question;

@Component
public class QuestionManagementService {

	private  RepositoryFactory repositoryFactory; //final
		
	@Transactional
	public List<Question> listQuestions(){
		QuestionRepository repository = repositoryFactory.createQuestionRepository();
		return repository.findAll();
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

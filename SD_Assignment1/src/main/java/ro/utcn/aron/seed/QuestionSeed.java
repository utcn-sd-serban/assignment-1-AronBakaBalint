package ro.utcn.aron.seed;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ro.utcn.aron.model.Question;
import ro.utcn.aron.persistence.api.QuestionRepository;
import ro.utcn.aron.persistence.api.RepositoryFactory;
import ro.utcn.aron.persistence.api.UserRepository;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {

	private final RepositoryFactory repositoryFactory;
	
	public QuestionSeed(RepositoryFactory repositoryFactory) {
		
		this.repositoryFactory = repositoryFactory;
	}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		
		QuestionRepository questionRepository = repositoryFactory.createQuestionRepository();
		if (questionRepository.findAll().isEmpty()) {
			questionRepository.save(new Question(0, "What is new in Java 8?", "Can anyone list some features?", "java,programming", "John", new Date().toString()));
			questionRepository.save(new Question(0, "Static fields", "Difference between statitic in java and c++?", "c++,static", "Kate", new Date().toString()));
		}
		
		UserRepository userRepository = repositoryFactory.createUserRepository();
		if (userRepository.findAll().isEmpty()) {
			userRepository.save("John", "1234");
			userRepository.save("Kate", "abcd");
		
		}
	}

}

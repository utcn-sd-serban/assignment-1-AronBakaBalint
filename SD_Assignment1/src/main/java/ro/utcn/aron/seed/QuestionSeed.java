package ro.utcn.aron.seed;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ro.utcn.aron.mode.RepositoryMode;
import ro.utcn.aron.model.Question;
import ro.utcn.aron.persistence.api.QuestionRepository;
import ro.utcn.aron.persistence.api.QuestionRepositoryFactory;
import ro.utcn.aron.persistence.api.UserRepository;
import ro.utcn.aron.persistence.api.UserRepositoryFactory;
import ro.utcn.aron.persistence.jdbc.JdbcQuestionRepositoryFactory;
import ro.utcn.aron.persistence.jdbc.JdbcUserRepositoryFactory;
import ro.utcn.aron.persistence.memory.InMemoryQuestionRepositoryFactory;
import ro.utcn.aron.persistence.memory.InMemoryUserRepositoryFactory;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {

	private final QuestionRepositoryFactory questionRepositoryFactory;
	
	private final UserRepositoryFactory userRepositoryFactory;
	
	public QuestionSeed() {
		if(RepositoryMode.repoMode.equals("JDBC")) {
			questionRepositoryFactory = new JdbcQuestionRepositoryFactory();
			userRepositoryFactory = new JdbcUserRepositoryFactory();
		}
		else {
			questionRepositoryFactory = new InMemoryQuestionRepositoryFactory();
			userRepositoryFactory = new InMemoryUserRepositoryFactory();
		}
	}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		
		QuestionRepository questionRepository = questionRepositoryFactory.createQuestionRepository();
		if (questionRepository.findAll().isEmpty()) {
			questionRepository.save(new Question(0, "What is new in Java 8?", "Can anyone list some features?", "java,programming", "John", new Date().toString()));
			questionRepository.save(new Question(0, "Static fields", "Difference between statitic in java and c++?", "c++,static", "Kate", new Date().toString()));
		}
		
		UserRepository userRepository = userRepositoryFactory.createUserRepository();
		if (userRepository.findAll().isEmpty()) {
			userRepository.save("John", "1234");
			userRepository.save("Kate", "abcd");
		
		}
	}

}

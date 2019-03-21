package ro.utcn.aron.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ro.utcn.aron.api.QuestionRepository;
import ro.utcn.aron.api.RepositoryFactory;
import ro.utcn.aron.api.UserRepository;
import ro.utcn.aron.model.Question;
import ro.utcn.aron.model.User;


@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {
	
	private RepositoryFactory factory; //final
	

	@Override
	@Transactional
	public void run(String... args) throws Exception {
			
		QuestionRepository repository = factory.createQuestionRepository();
		if(repository.findAll().isEmpty()) {
			repository.save(new Question(1, "Are all apples red?", "I've only seen red apples", "apple red" ));
			repository.save(new Question(2, "How can I extend String class in Java?", "I want to add more features to the String class", "java programming" ));
		}
			
		UserRepository userRepository = factory.createUserRepository();
		if(userRepository.findAll().isEmpty()) {
			userRepository.save(new User(0, "abcd", "1234"));
			userRepository.save(new User(0, "aron", "4567"));
		}
			
	}

}

package seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import api.QuestionRepository;
import api.RepositoryFactory;
import model.Question;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {
	private final RepositoryFactory factory;
	
		public QuestionSeed(RepositoryFactory factory) {
			this.factory = factory;
		}

		@Override
		@Transactional
		public void run(String... args) throws Exception {
			
			System.out.println("App started");
			
			QuestionRepository repository = factory.createQuestionRepository();
			if(repository.findAll().isEmpty()) {
				repository.save(new Question(1, "Are all apples red?", "I've only seen red apples" ));
				repository.save(new Question(2, "How can I extend String class in Java?", "I want to add more features to the String class" ));
			}
			
		}

}

package ro.utcn.aron.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import ro.utcn.aron.api.QuestionRepository;
import ro.utcn.aron.api.RepositoryFactory;
import ro.utcn.aron.api.UserRepository;

@Component
@ConditionalOnProperty(name = "aron.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {

	private final InMemoryQuestionRepository questionRepository = new InMemoryQuestionRepository();
	private final InMemoryUserRepository userRepository = new InMemoryUserRepository();

	@Override
	public QuestionRepository createQuestionRepository() {
		return questionRepository;
	}

	@Override
	public UserRepository createUserRepository() {
		return userRepository;
	}
	
	
}

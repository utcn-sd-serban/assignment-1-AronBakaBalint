package ro.utcn.aron.jdbc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import ro.utcn.aron.api.QuestionRepository;
import ro.utcn.aron.api.RepositoryFactory;
import ro.utcn.aron.api.UserRepository;
import ro.utcn.aron.memory.InMemoryUserRepository;

@Component
@ConditionalOnProperty(name = "aron.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory{

	private final JdbcQuestionRepository questionRepository = new JdbcQuestionRepository();
	private final InMemoryUserRepository userRepository = new InMemoryUserRepository();
	
	@Override
	public QuestionRepository createQuestionRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRepository createUserRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}

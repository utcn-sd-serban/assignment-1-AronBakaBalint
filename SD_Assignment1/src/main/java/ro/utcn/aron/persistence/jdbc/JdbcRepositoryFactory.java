package ro.utcn.aron.persistence.jdbc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ro.utcn.aron.persistence.api.QuestionRepository;
import ro.utcn.aron.persistence.api.RepositoryFactory;
import ro.utcn.aron.persistence.api.UserRepository;

@Component
@ConditionalOnProperty(name="aron.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {

	private final QuestionRepository questionRepository;
	
	private final UserRepository userRepository;
	
	public JdbcRepositoryFactory(JdbcTemplate template) {
		this.questionRepository = new  JdbcQuestionRepository(template);
		this.userRepository = new JdbcUserRepository(template);
	}
	
	@Override
	public QuestionRepository createQuestionRepository() {
		
		return questionRepository;
	}


	@Override
	public UserRepository createUserRepository() {

		return userRepository;
	}

}

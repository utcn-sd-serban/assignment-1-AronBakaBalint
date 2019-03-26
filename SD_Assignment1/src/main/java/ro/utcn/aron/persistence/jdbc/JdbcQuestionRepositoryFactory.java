package ro.utcn.aron.persistence.jdbc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import ro.utcn.aron.persistence.api.QuestionRepository;
import ro.utcn.aron.persistence.api.QuestionRepositoryFactory;

@Component
@ConditionalOnProperty(name="aron.repository-type", havingValue = "JDBC")
public class JdbcQuestionRepositoryFactory implements QuestionRepositoryFactory {

	private final QuestionRepository repository = new JdbcQuestionRepository();
	
	
	@Override
	public QuestionRepository createQuestionRepository() {
		
		return repository;
	}

}

package ro.utcn.aron.persistence.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import ro.utcn.aron.persistence.api.QuestionRepository;
import ro.utcn.aron.persistence.api.QuestionRepositoryFactory;

@Component
@ConditionalOnProperty(name="aron.repository-type", havingValue = "MEMORY")
public class InMemoryQuestionRepositoryFactory implements QuestionRepositoryFactory {

	private final InMemoryQuestionRepository repository = new InMemoryQuestionRepository();
	
	@Override
	public QuestionRepository createQuestionRepository() {
		
		return repository;
	}

}

package ro.utcn.aron.persistence.api;

import org.springframework.stereotype.Component;

@Component
public interface RepositoryFactory {

	QuestionRepository createQuestionRepository();
	
	UserRepository createUserRepository();
}

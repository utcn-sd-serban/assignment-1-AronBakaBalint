package ro.utcn.aron.api;

import org.springframework.stereotype.Component;

@Component
public interface RepositoryFactory {

	QuestionRepository createQuestionRepository();
	UserRepository createUserRepository();
}

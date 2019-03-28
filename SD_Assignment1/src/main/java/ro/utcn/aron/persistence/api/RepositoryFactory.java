package ro.utcn.aron.persistence.api;

public interface RepositoryFactory {

	QuestionRepository createQuestionRepository();
	
	UserRepository createUserRepository();
}

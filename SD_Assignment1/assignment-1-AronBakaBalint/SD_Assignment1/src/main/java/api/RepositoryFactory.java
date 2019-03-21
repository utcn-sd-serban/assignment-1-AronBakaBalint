package api;

public interface RepositoryFactory {

	QuestionRepository createQuestionRepository();
	UserRepository createUserRepository();
	
}

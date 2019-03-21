package service;

import api.RepositoryFactory;
import api.UserRepository;

public class UserManagementService {

	private final RepositoryFactory repositoryFactory;
	
		public UserManagementService(RepositoryFactory repositoryFactory) {
			this.repositoryFactory = repositoryFactory;
		}

	public boolean matches(String username, String password) {
		UserRepository repository = repositoryFactory.createUserRepository();
		return repository.matches(username, password);
	}
}

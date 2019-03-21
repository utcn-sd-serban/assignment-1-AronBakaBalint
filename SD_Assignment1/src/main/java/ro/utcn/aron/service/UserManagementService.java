package ro.utcn.aron.service;

import org.springframework.stereotype.Component;

import ro.utcn.aron.api.RepositoryFactory;
import ro.utcn.aron.api.UserRepository;

@Component
public class UserManagementService {

	private  RepositoryFactory repositoryFactory; //final
	 
	public boolean matches(String username, String password) {
		UserRepository repository = repositoryFactory.createUserRepository();
		return repository.matches(username, password);
	}
}

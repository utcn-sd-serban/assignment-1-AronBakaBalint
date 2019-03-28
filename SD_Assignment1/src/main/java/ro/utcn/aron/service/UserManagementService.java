package ro.utcn.aron.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ro.utcn.aron.model.User;
import ro.utcn.aron.persistence.api.RepositoryFactory;

@Component
public class UserManagementService {

	private final RepositoryFactory repositoryFactory;
	
	public UserManagementService(RepositoryFactory userRepositoryFactory) {
		
		this.repositoryFactory = userRepositoryFactory;
	}
	
	@Transactional
	public boolean matches(String username, String password) {
		return repositoryFactory.createUserRepository().matches(username, password);
	}
	
	@Transactional
	public void save(String username, String password) {
		repositoryFactory.createUserRepository().save(username, password);
	}
	
	@Transactional
	public List<User> findAll() {
		return repositoryFactory.createUserRepository().findAll();
	}
}

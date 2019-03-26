package ro.utcn.aron.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ro.utcn.aron.mode.RepositoryMode;
import ro.utcn.aron.model.User;
import ro.utcn.aron.persistence.api.UserRepositoryFactory;
import ro.utcn.aron.persistence.jdbc.JdbcUserRepositoryFactory;
import ro.utcn.aron.persistence.memory.InMemoryUserRepositoryFactory;

public class UserManagementService {

	private final UserRepositoryFactory userRepositoryFactory;
	
	public UserManagementService() {
		
		if(RepositoryMode.repoMode.equals("JDBC"))
			userRepositoryFactory = new JdbcUserRepositoryFactory();
		else
			userRepositoryFactory = new InMemoryUserRepositoryFactory();
	}
	
	@Transactional
	public boolean matches(String username, String password) {
		return userRepositoryFactory.createUserRepository().matches(username, password);
	}
	
	@Transactional
	public void save(String username, String password) {
		userRepositoryFactory.createUserRepository().save(username, password);
	}
	
	@Transactional
	public List<User> findAll() {
		return userRepositoryFactory.createUserRepository().findAll();
	}
}

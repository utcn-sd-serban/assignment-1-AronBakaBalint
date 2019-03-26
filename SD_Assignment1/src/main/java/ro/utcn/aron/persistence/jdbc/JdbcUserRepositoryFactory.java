package ro.utcn.aron.persistence.jdbc;

import ro.utcn.aron.persistence.api.UserRepository;
import ro.utcn.aron.persistence.api.UserRepositoryFactory;

public class JdbcUserRepositoryFactory implements UserRepositoryFactory{

	private final JdbcUserRepository repository = new JdbcUserRepository();
	
	@Override
	public UserRepository createUserRepository() {
		return repository;
	}
	
	
}

package ro.utcn.aron.persistence.memory;

import ro.utcn.aron.persistence.api.UserRepository;
import ro.utcn.aron.persistence.api.UserRepositoryFactory;

public class InMemoryUserRepositoryFactory implements UserRepositoryFactory {

	private final InMemoryUserRepository repository = new InMemoryUserRepository();
	
	@Override
	public UserRepository createUserRepository() {
		return repository;
	}

}

package ro.utcn.aron.persistence.api;

import java.util.List;

import ro.utcn.aron.model.User;

public interface UserRepository {

	boolean matches(String username, String password);
	
	void save(String username, String password);
	
	List<User> findAll();
}

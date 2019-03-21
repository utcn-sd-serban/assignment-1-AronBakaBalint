package api;

import java.util.Optional;

import model.User;

public interface UserRepository {

	boolean matches(String username, String password);
	
	User save(User user);
	
	Optional<User> findByID(int id);
	
	void remove(User user);
	
	
}

package ro.utcn.aron.api;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ro.utcn.aron.model.User;

@Component
public interface UserRepository {

	boolean matches(String username, String password);
	
	User save(User user);
	
	Optional<User> findByID(int id);
	
	List<User> findAll();
	
	void remove(User user);
	
	
}

package memory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import api.UserRepository;
import model.User;

public class InMemoryUserRepository implements UserRepository {

	private AtomicInteger currentId = new AtomicInteger(0);
	private Map<Integer, User> users = new ConcurrentHashMap<>();
	
	@Override
	public boolean matches(String username, String password) {
		for (Map.Entry<Integer, User> entry : users.entrySet()) {
		    if(entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password))
		    	return true;
		}
		return false;
	}

	@Override
	public synchronized User save(User user) {
		if(user.getId() != 0) {
			users.put(user.getId(), user);
		} else {
			user.setId(currentId.get());
			currentId.set(currentId.get()+1);
			users.put(user.getId(), user);
		}
		return user;
	}

	@Override
	public Optional<User> findByID(int id) {
		return Optional.ofNullable(users.get(id));
	}

	@Override
	public void remove(User user) {
		users.remove(user.getId());	
	}
}

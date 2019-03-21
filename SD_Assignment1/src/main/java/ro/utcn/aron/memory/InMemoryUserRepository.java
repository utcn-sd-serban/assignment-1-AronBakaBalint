package ro.utcn.aron.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import ro.utcn.aron.api.UserRepository;
import ro.utcn.aron.model.User;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryUserRepository implements UserRepository {

	private  AtomicInteger currentId = new AtomicInteger(0);
	private  Map<Integer, User> users = new ConcurrentHashMap<>();
	
	@Override
	public boolean matches(String username, String password) {
		final long[] i = {0};
		users.forEach((k, v) -> {
			if(v.getPassword().equals(password) && v.getUsername().equals(username)) {
					i[0] = 1;
				}
		});

		return i[0] == 1;
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

	@Override
	public List<User> findAll() {
		return new ArrayList<>(users.values());
	}
}

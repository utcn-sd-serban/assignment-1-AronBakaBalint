package ro.utcn.aron.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ro.utcn.aron.api.UserRepository;
import ro.utcn.aron.model.User;

@Component
public class JdbcUserRepository implements UserRepository {

	@Override
	public boolean matches(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

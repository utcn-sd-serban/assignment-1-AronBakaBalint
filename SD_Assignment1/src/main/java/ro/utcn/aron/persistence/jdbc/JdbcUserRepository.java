package ro.utcn.aron.persistence.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import ro.utcn.aron.model.User;
import ro.utcn.aron.persistence.api.UserRepository;

public class JdbcUserRepository implements UserRepository{

	private static final JdbcTemplate template = new JdbcTemplate();

	public JdbcUserRepository() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/a1");
		dataSource.setUsername("postgres");
		dataSource.setPassword("1234");
		template.setDataSource(dataSource);
	}
	
	@Override
	public boolean matches(String username, String password) {
		User user = template.query("SELECT * FROM users WHERE username LIKE ?", (resultSet, i) -> new User(
				resultSet.getString("username"), resultSet.getString("password")),username).get(0);
		
		return user.getPassword().equals(password);
	}

	@Override
	public void save(String name, String password) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
		insert.setTableName("users");
		insert.setGeneratedKeyName("id");
		
		Map<String, String> data = new HashMap<>();
		data.put("username", name);
		data.put("password", password);
		
		insert.execute(data);
		
	}

	@Override
	public List<User> findAll() {
		return template.query("SELECT * FROM users", (resultSet, i) -> new User(
				resultSet.getString("username"), resultSet.getString("password")));
	}
	

}

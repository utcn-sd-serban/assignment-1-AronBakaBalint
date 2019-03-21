package jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import api.QuestionRepository;
import model.Question;

public class JdbcQuestionRepository implements QuestionRepository {

	private final JdbcTemplate template;

	
	public JdbcQuestionRepository(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public Question save(Question question) {
		if(question.getId() != 0) {
			update(question);
		} else {
			int id = insert(question);
			question.setId(id);
		}
		return null;
	}

	@Override
	public Optional<Question> findByID(int id) {
		List<Question> questions = template.query("SELECT * FROM question WHERE id = ?"+id, 
				new Object[] { id },
				(resultSet, i) -> 
		new Question(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("body"), resultSet.getString("tags")));
		return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
	}

	@Override
	public void remove(Question question) {
		template.update("DELETE * FROM question WHERE id = ?", question.getId());
		
	}

	@Override
	public List<Question> findAll() {
		return template.query("SELECT * FROM question", (resultSet, i) -> 
			new Question(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("body"), resultSet.getString("tags")));
	}
	
	private int insert(Question question) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
		insert.setTableName("question");
		insert.setGeneratedKeyName("id");
		
		Map<String, Object> data = new HashMap<>();
		data.put("title", question.getTitle());
		data.put("text", question.getBody());
		
		return insert.executeAndReturnKey(data).intValue();
	}
	
	private void update(Question question) {
		template.update("UPDATE question SET title = ?, text = ? WHERE id = ?  ",
				question.getTitle(), question.getBody(), question.getId());
	}

	@Override
	public Optional<List<Question>> filterByTitle(String title) {
		List<Question> result =  template.query("SELECT * FROM question WHERE question.title = ?", (resultSet, i) -> 
		new Question(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("body"), resultSet.getString("tags")), title);
		
		return result.isEmpty() ? Optional.empty() : Optional.of(result);
	}

	@Override
	public Optional<List<Question>> filterByTag(String tag) {
		List<Question> result =  template.query("SELECT * FROM question JOIN tags ON question.id = tags.questionid WHERE tags.tags LIKE ?", (resultSet, i) -> 
		new Question(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("body"), resultSet.getString("tags")), "'%+tag+%'");
		
		return result.isEmpty() ? Optional.empty() : Optional.of(result);
	}
}

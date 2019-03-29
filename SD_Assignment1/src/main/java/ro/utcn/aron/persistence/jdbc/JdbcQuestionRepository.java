package ro.utcn.aron.persistence.jdbc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import ro.utcn.aron.model.Answer;
import ro.utcn.aron.model.Question;
import ro.utcn.aron.persistence.api.QuestionRepository;


public class JdbcQuestionRepository implements QuestionRepository {

	private  final JdbcTemplate template;

	public JdbcQuestionRepository(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Question save(Question question) {
		if (question.getId() != 0) {
			update(question);
		} else {
			int id = insert(question);
			question.setId(id);
		}

		return question;
	}

	@Override
	public Optional<Question> findById(int id) {
		List<Question> questions = template.query("SELECT * FROM question WHERE id = ?", new Object[] { id },
				(resultSet, i) -> new Question(resultSet.getInt("id"), resultSet.getString("title"),
						resultSet.getString("body"), list2String(getTagsFromJdbc(resultSet.getInt("id"))),
						resultSet.getString("author"), resultSet.getString("creationdate")));

		return questions.isEmpty() ? Optional.empty() : Optional.ofNullable(questions.get(0));
	}

	@Override
	public void remove(Question question) {
		template.update("DELETE FROM question WHERE id = ?", question.getId());
	}

	@Override
	public List<Question> findAll() {
		return template.query("SELECT * FROM question", (resultSet,
				i) -> new Question(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("body"),
						list2String(getTagsFromJdbc(resultSet.getInt("id"))), resultSet.getString("author"),
						resultSet.getString("creationdate"),getAnswersFromJdbc(resultSet.getInt("id"))));
	}

	private int insert(Question question) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
		insert.setTableName("question");
		insert.setGeneratedKeyName("id");

		Map<String, Object> data = new HashMap<>();
		data.put("title", question.getTitle());
		data.put("body", question.getBody());
		data.put("author", question.getAuthor());

		data.put("creationdate", new Date().toString());
		int questionId = insert.executeAndReturnKey(data).intValue();

		SimpleJdbcInsert insert2 = new SimpleJdbcInsert(template);
		insert2.setTableName("tags");
		insert2.setGeneratedKeyName("tagid");

		Map<String, Object> data2 = new HashMap<>();
		String[] tags = question.getTags().split(",");
		for (int i = 0; i < tags.length; i++) {
			data2.put("questionid", questionId);
			data2.put("tags", tags[i]);
			insert2.execute(data2);
		}

		return questionId;
	}

	private void update(Question question) {
		template.update("UPDATE question SET title = ?, body = ?, author = ?, creationdate = ? WHERE id = ? ",
				question.getTitle(), question.getBody(), question.getAuthor(), question.getCreationDate(),
				question.getId());
	}

	@Override
	public List<Question> filterByTitle(String title) {
		return template
				.query("SELECT * FROM question",
						(resultSet, i) -> new Question(resultSet.getInt("id"), resultSet.getString("title"),
								resultSet.getString("body"), list2String(getTagsFromJdbc(resultSet.getInt("id"))),
								resultSet.getString("author"), resultSet.getString("creationdate"),getAnswersFromJdbc(resultSet.getInt("id"))))
				.stream().filter(q -> q.getTitle().contains(title)).collect(Collectors.toList());
	}

	@Override
	public List<Question> filterByTag(String tag) {
		return template.query(
				"SELECT * FROM question JOIN tags ON question.id = tags.questionid WHERE tags.tags LIKE '%" + tag
						+ "%'",
				(resultSet, i) -> new Question(resultSet.getInt("id"), resultSet.getString("title"),
						resultSet.getString("body"), list2String(getTagsFromJdbc(resultSet.getInt("id"))),
						resultSet.getString("author"), resultSet.getString("author"),getAnswersFromJdbc(resultSet.getInt("id"))));
	}

	private List<String> getTagsFromJdbc(int questionId) {
		return template.query("SELECT * FROM tags WHERE questionid = ?",
				(resultSet, i) -> new String(resultSet.getString("tags")), questionId);
	}

	private String list2String(List<String> tagList) {
		String text = "";
		for (int i = 0; i < tagList.size() - 1; i++) {
			text += tagList.get(i) + ",";
		}
		text += tagList.get(tagList.size() - 1);

		return text;
	}

	@Override
	public void answerQuestion(int questionid, String user, String answer) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
		insert.setTableName("answers");
		insert.setGeneratedKeyName("answerid");

		Map<String, Object> data = new HashMap<>();
		data.put("questionid", questionid);
		data.put("author", user);
		data.put("answer", answer);

		data.put("creationdate", new Date().toString());
		insert.execute(data);
	}

	private List<Answer> getAnswersFromJdbc(int questionid) {
		return template.query("SELECT * FROM answers WHERE questionid = ?",
				(resultSet, i) -> new Answer(resultSet.getInt("answerid"),resultSet.getString("answer"), resultSet.getString("author"),
						resultSet.getString("creationdate")),
				questionid);
	}

	@Override
	public void editQuestion(int questionid, String user, String text) {
		List<Question> result = template.query("SELECT * FROM question WHERE id = ? and author = ?", (resultSet,
				i) -> new Question(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("body"),
						list2String(getTagsFromJdbc(resultSet.getInt("id"))), resultSet.getString("author"),
						resultSet.getString("creationdate"),getAnswersFromJdbc(resultSet.getInt("id"))), questionid, user);
		
		if(result.isEmpty()) {
			System.out.println("You cannot edit other question!");
			return;
		}
		
		template.update("UPDATE question SET body = ? WHERE id = ? ",
				text, questionid);
	}

	@Override
	public void removeAnswer(int answerid, String user) {
		List<Answer> result = template.query("SELECT * FROM answers WHERE answerid = ? and author = ?", (resultSet,
				i) -> new Answer(resultSet.getInt("answerid"), resultSet.getString("author"),
						 resultSet.getString("answer"),
						resultSet.getString("creationdate")), answerid, user);
		
		if(result.isEmpty()) {
			System.out.println("You can delete your answer only!");
			return;
		}
		
		template.update("DELETE FROM answers WHERE answerid = ? ",answerid);
	}

	@Override
	public void editAnswer(int answerid, String user, String newText) {
		List<Answer> result = template.query("SELECT * FROM answers WHERE answerid = ? and author = ?", (resultSet,
				i) -> new Answer(resultSet.getInt("answerid"), resultSet.getString("author"),
						 resultSet.getString("answer"),
						resultSet.getString("creationdate")), answerid, user);
		
		if(result.isEmpty()) {
			System.out.println("You can edit your answer only!");
			return;
		}
		
		template.update("UPDATE answers SET answer = ? WHERE answerid = ? ",
				newText, answerid);
		
	}

	@Override
	public void upVoteAnswer(String username, int answerid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downVoteAnswer(String username, int answerid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upVoteQuestion(String username, int answerid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downVoteQuestion(String username, int answerid) {
		// TODO Auto-generated method stub
		
	}
}

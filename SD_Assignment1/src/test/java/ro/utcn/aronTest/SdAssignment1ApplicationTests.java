package ro.utcn.aronTest;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ro.utcn.aron.persistence.api.RepositoryFactory;
import ro.utcn.aron.persistence.memory.InMemoryRepositoryFactory;
import ro.utcn.aron.persistence.memory.InMemoryUserRepository;
import ro.utcn.aron.service.QuestionManagementService;
import ro.utcn.aron.service.UserManagementService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { InMemoryUserRepository.class })
public class SdAssignment1ApplicationTests {

	RepositoryFactory memRepositoryFactory = new InMemoryRepositoryFactory();

	@Test
	public void testAddQuestion() {
		QuestionManagementService questionManagementService = new QuestionManagementService(memRepositoryFactory);
		questionManagementService.addQuestion("Save Question Test", "I'm testing save question method", "test,memory",
				"Paul");

		assertEquals( questionManagementService.listQuestions().size() , 1);
	}

	@Test
	public void testUserLogin() {
		UserManagementService userManagementService = new UserManagementService(memRepositoryFactory);
		userManagementService.save("Rose", "abc123");

		assertEquals( userManagementService.matches("Rose", "abc123"), true);
	}

	@Test
	public void testAddAnswer() {
		QuestionManagementService questionManagementService = new QuestionManagementService(memRepositoryFactory);
		questionManagementService.addQuestion("Save Question Test", "I'm testing save question method", "test,memory",
				"Paul");
		questionManagementService.answerQuestion("Cait", 1, "It was ok");

		assertEquals( questionManagementService.listQuestions().stream().mapToInt(q -> q.getAnswers().size()).sum() , 1);
	}

	@Test
	public void testDeleteAnswer1() { // when the answer belongs to the user
		QuestionManagementService questionManagementService = new QuestionManagementService(memRepositoryFactory);
		questionManagementService.addQuestion("Save Question Test", "I'm testing save question method", "test,memory",
				"Paul");
		questionManagementService.answerQuestion("Cait", 1, "It was ok");
		questionManagementService.deleteAnswer("Cait", 1);

		assertEquals( questionManagementService.listQuestions().stream().mapToInt(q -> q.getAnswers().size()).sum() , 0);
	}

	@Test
	public void testDeleteAnswer2() { // when the answer does not belong to the user
		QuestionManagementService questionManagementService = new QuestionManagementService(memRepositoryFactory);
		questionManagementService.addQuestion("Save Question Test", "I'm testing save question method", "test,memory",
				"Paul");
		questionManagementService.answerQuestion("Cait", 1, "It was ok");
		questionManagementService.deleteAnswer("John", 1);

		assertEquals( questionManagementService.listQuestions().stream().mapToInt(q -> q.getAnswers().size()).sum() , 1);
	}

	@Test
	public void testEditAnswer() { 
		QuestionManagementService questionManagementService = new QuestionManagementService(memRepositoryFactory);
		questionManagementService.addQuestion("Save Question Test", "I'm testing save question method", "test,memory",
				"Paul");
		questionManagementService.answerQuestion("Cait", 1, "It was ok");
		questionManagementService.editAnswer("Cait", 1, "Edited answer");

		assertEquals( questionManagementService.listQuestions().stream().filter(q -> q.getId() == 1)
				.collect(Collectors.toList()).get(0).getAnswers().stream().filter(a -> a.getId() == 1)
				.collect(Collectors.toList()).get(0).getText(), "Edited answer");

	}
	
	@Test
	public void testFilterByTag() { 
		QuestionManagementService questionManagementService = new QuestionManagementService(memRepositoryFactory);
		questionManagementService.addQuestion("Save Question Test", "I'm testing save question method", "test,memory",
				"Paul");

		assertEquals( questionManagementService.filterByTag("mem").size(), 1);

	}
}

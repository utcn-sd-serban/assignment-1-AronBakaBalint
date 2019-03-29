package ro.utcn.aron.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ro.utcn.aron.model.Question;
import ro.utcn.aron.service.QuestionManagementService;
import ro.utcn.aron.service.UserManagementService;

@Component
public class ConsoleController implements CommandLineRunner {

	private final Scanner scanner = new Scanner(System.in);
	
	private final QuestionManagementService questionManagementService;
	
	private final UserManagementService userManagementService;
	
	
	public ConsoleController(QuestionManagementService questionManagementService,UserManagementService userManagementService) {
		super();
		this.questionManagementService = questionManagementService;
		this.userManagementService = userManagementService;
	}

	@Override
	public void run(String... args) throws Exception {
		boolean okPassword = false;
		String user = null;
		boolean done = false;
		boolean loggedIn = false;
		
		while(!done) {
			
			if(!loggedIn)
			while (!okPassword) {
				try {
					user = handleUsernameAndPassword();
					if(!user.equals("")) {
						okPassword = true;
						print("Welcome to StackOverflow, "+user+"!");
					}
					if(okPassword == false)
						System.out.println("Wrong username or password");
				} catch(Exception e) {
					System.out.println("Wrong username or password");
				}
				
			}
			
			print("Enter command: ");
			String command = scanner.next().trim().toLowerCase();
			if(command.equalsIgnoreCase("logoff")) {
				done = false;
				loggedIn = false;
				okPassword = false;
			} else
			done = handleCommand(command, user);
		}

	}

	private String handleUsernameAndPassword() {
		print("Enter username: ");
		String username = scanner.next().trim();
		print("Enter password: ");
		String password = scanner.next().trim();
		if(userManagementService.matches(username, password)) return username;
		return "";
	}

	private boolean handleCommand(String command, String user) {
		switch (command) {
		case "list":
			handleList();
			return false;
		case "add":
			handleAdd(user);
			return false;
		case "remove":
			handleRemove();
			return false;
		case "filterbytitle":
			handleFilterByTitle();
			return false;
		case "filterbytag":
			handleFilterByTag();
			return false;
		case "answer":
			handleAnswer(user);
			return false;
		case "editquestion":
			handleEditQuestion(user);
			return false;
		case "deleteanswer":
			handleDeleteAnswer(user);
			return false;
		case "editanswer":
			handleEditAnswer(user);
			return false;
		case "voteanswer":
			handleVoteAnswer(user);
			return false;
		case "votequestion":
			handleVoteQuestion(user);
			return false;
		case "exit":
			return true;
		default:
			print("Unknown command. Try again.");
			return false;
		}
	}

	private void handleFilterByTag() {
		System.out.println("Tag: ");
		String tag = scanner.next();
		List<Question> listedQuestions = questionManagementService.filterByTag(tag);
		listedQuestions.forEach(q -> {
			System.out.println(q);
		});
	}

	private void handleFilterByTitle() {
		System.out.println("Title: ");
		String title = scanner.next();
		List<Question> listedQuestions = questionManagementService.filterByTitle(title);
		listedQuestions.forEach(q -> {
			System.out.println(q);
		});

	}

	private void handleRemove() {
		try{
			print("Question id = ");
			int id = scanner.nextInt();
			questionManagementService.removeQuestion(id);
		} catch (Exception e) {
			System.out.println("No question with given ID found!");
		}
	}

	private void handleAdd(String user) {
		print("Question title: ");
		scanner.nextLine();
		String title = scanner.nextLine();
		print("Text: ");
		String text = scanner.nextLine();
		print("Tags: ");
		String tags = scanner.nextLine();
		questionManagementService.addQuestion(title, text, tags, user);
	}

	private void handleList() {
		List<Question> listedQuestions = questionManagementService.listQuestions();
		listedQuestions.forEach(q -> {
			System.out.println(q);
		});

	}
	
	private void handleAnswer(String user) {
		print("Question id: ");
		int id = scanner.nextInt();
		print("Answer: ");
		scanner.nextLine();
		String text = scanner.nextLine().trim();
		
		questionManagementService.answerQuestion(user, id, text);
	}
	
	private void handleEditQuestion(String user) {
		print("Question id: ");
		int id = scanner.nextInt();
		print("Text: ");
		scanner.nextLine();
		String text = scanner.nextLine().trim();
		
		questionManagementService.editQuestion(user, id, text);
	}
	
	private void handleDeleteAnswer(String user) {
		print("Answer id: ");
		int id = scanner.nextInt();
		
		questionManagementService.deleteAnswer(user, id);
	}
	
	private void handleEditAnswer(String user) {
		print("Answer id: ");
		int id = scanner.nextInt();
		print("Text: ");
		scanner.nextLine();
		String text = scanner.nextLine().trim();
		
		questionManagementService.editAnswer(user, id, text);
	}
	
	private void handleVoteAnswer(String user) {
		print("answer id:");
		int answerid = scanner.nextInt();
		print("vote type:");
		int type = scanner.nextInt();
		
		if(type < 0)
			questionManagementService.downVoteAnswer(user, answerid);
		else
			questionManagementService.upVoteAnswer(user, answerid);
	}
	
	private void handleVoteQuestion(String user) {
		print("question id:");
		int questionid = scanner.nextInt();
		print("vote type:");
		int type = scanner.nextInt();
		
		if(type < 0)
			questionManagementService.downVoteQuestion(user, questionid);
		else
			questionManagementService.upVoteQuestion(user, questionid);
	}

	private void print(String s) {
		System.out.println(s);
	}

}

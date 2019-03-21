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

	private  QuestionManagementService questionManagementService; //final

	private  UserManagementService userManagementService; //final
	 

	@Override
	public void run(String... args) throws Exception {
		boolean okPassword = false;

		while (!okPassword) {
			okPassword = handleUsernameAndPassword();
		}

		print("Welcome to StackOverflow!");
		boolean done = false;
		while (!done) {
			print("Enter command: ");
			String command = scanner.next().trim().toLowerCase();

			done = handleCommand(command);
		}

	}

	private boolean handleUsernameAndPassword() {
		print("Enter username: ");
		String username = scanner.next().trim();
		print("Enter password: ");
		String password = scanner.next().trim();
		return userManagementService.matches(username, password);
	}

	private boolean handleCommand(String command) {
		switch (command) {
		case "list":
			handleList();
			return false;
		case "add":
			handleAdd();
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
		case "exit":
			return true;
		default:
			print("Unknown command. Try again.");
			return false;
		}
	}

	private void handleFilterByTag() {

	}

	private void handleFilterByTitle() {
		// TODO Auto-generated method stub

	}

	private void handleRemove() {
		print("Question id = ");
		int id = scanner.nextInt();
		questionManagementService.removeQuestion(id);

	}

	private void handleAdd() {
		print("Question title: ");
		String title = scanner.next().trim();
		print("Text: ");
		String text = scanner.next().trim();
		print("Tags: ");
		String tags = scanner.next().trim();
		questionManagementService.save(title, text, tags);
	}

	private void handleList() {
		List<Question> listedQuestions = questionManagementService.listQuestions();
		listedQuestions.forEach(q -> {
			System.out.println(q);
		});

	}

	private void print(String s) {
		System.out.println(s);
	}

}

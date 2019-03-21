package consoleController;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import service.QuestionManagementService;

@Component
public class ConsoleController implements CommandLineRunner {

	private final Scanner scanner = new Scanner(System.in);
	
	private final QuestionManagementService questionManagementService;
	
		public ConsoleController(QuestionManagementService qms) {
			this.questionManagementService = qms;
		}
	
	@Override
	public void run(String... args) throws Exception {
		boolean okPassword = false;
				
		while(!okPassword) {
			okPassword = handleUsernameAndPassword();
		}
		
		print("Welcome to StackOverflow!");
		boolean done = false;
		while(!done) {
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
		return false;
	}

	private boolean handleCommand(String command) {
		switch(command) {
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
		default: print("Unknown command. Try again.");
			return false;
		}
	}
	
	private void handleFilterByTag() {
		// TODO Auto-generated method stub
		
	}

	private void handleFilterByTitle() {
		// TODO Auto-generated method stub
		
	}

	private void handleRemove() {
		// TODO Auto-generated method stub
		
	}

	private void handleAdd() {
		// TODO Auto-generated method stub
		
	}

	private void handleList() {
		// TODO Auto-generated method stub
		
	}

	private void print(String s) {
		System.out.println(s);
	}
	
}

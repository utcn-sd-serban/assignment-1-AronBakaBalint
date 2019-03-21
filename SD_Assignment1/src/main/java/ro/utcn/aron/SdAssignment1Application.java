package ro.utcn.aron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ro.utcn.aron.memory.InMemoryQuestionRepository;

@SpringBootApplication
public class SdAssignment1Application {

	public static void main(String[] args) {
		InMemoryQuestionRepository imqr = new InMemoryQuestionRepository();
		SpringApplication.run(SdAssignment1Application.class, args);
	}

}

package ro.utcn.aronTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ro.utcn.aron.mode.RepositoryMode;
import ro.utcn.aron.service.UserManagementService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {UserManagementService.class, RepositoryMode.class})
public class SdAssignment1ApplicationTests {

	
	@Test
	public void testUserSave() {
		UserManagementService userManagementService = new UserManagementService();
		userManagementService.save("Frank", "1234a");
		assert userManagementService.findAll().size() == 1;	
	}
	
	@Test
	public void testUserLogin() {
		UserManagementService userManagementService = new UserManagementService();
		userManagementService.save("Frank", "1234a");
		assert userManagementService.matches("Frank", "1234a");	
	}

}

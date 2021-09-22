package com.hsbc;

import com.hsbc.domain.User;
import com.hsbc.service.UserService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class UserServiceTest {

	@Autowired
	private UserService userService;

	private User dbUser;

	@BeforeEach
	public void setup(){
		User user = new User();
		user.setDob("1/1/2000");
		user.setFirstName("Homer");
		user.setLastName("Simpson");
		user.setTile("Mr");

		dbUser = userService.create(user);
	}


	@AfterEach
	public void tearDown(){
		userService.delete(dbUser.getId());
		List<User> foundUsers  = userService.findByFirstName(dbUser.getFirstName());
		MatcherAssert.assertThat(foundUsers.size(), CoreMatchers.equalTo(0));
	}


	@Test
	public void testFindByFirstName(){
	  List<User> foundUsers  = userService.findByFirstName(dbUser.getFirstName());
	  MatcherAssert.assertThat(foundUsers.size(), CoreMatchers.equalTo(1));
	}

	@Test
	public void testFindByLastName(){
		List<User> foundUsers  = userService.findByLastName(dbUser.getLastName());
		MatcherAssert.assertThat(foundUsers.size(), CoreMatchers.equalTo(1));
	}

	@Test
	public void testFindById(){
		User foundUser  = userService.findById(dbUser.getId());
		MatcherAssert.assertThat(foundUser.getFirstName(), CoreMatchers.equalTo(dbUser.getFirstName()));
	}

	@Test
	public void testUpdateUser(){
		dbUser.setTile("Mrs");
		User foundUser  = userService.update(dbUser);
		MatcherAssert.assertThat(foundUser.getTile(), CoreMatchers.equalTo("Mrs"));
	}

}

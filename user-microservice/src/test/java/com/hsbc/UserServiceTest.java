package com.hsbc;


import com.hsbc.domain.User;
import com.hsbc.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
		user.setJobTitle("Nuclear Technician");

		dbUser = userService.create(user);
	}


	@AfterEach
	public void tearDown(){
		userService.delete(dbUser.getId());
		List<User> foundUsers  = userService.findByFirstName(dbUser.getFirstName());
		assertThat(foundUsers.size(), equalTo(0));
	}


	@Test
	public void testFindByFirstName(){
	  List<User> foundUsers  = userService.findByFirstName(dbUser.getFirstName());
	  assertThat(foundUsers.size(), equalTo(1));
	}

	@Test
	public void testFindByLastName(){
		List<User> foundUsers  = userService.findByLastName(dbUser.getLastName());
		assertThat(foundUsers.size(), equalTo(1));
	}

	@Test
	public void testFindById(){
		User foundUser  = userService.findById(dbUser.getId());
		assertThat(foundUser.getFirstName(), equalTo(dbUser.getFirstName()));
	}

	@Test
	public void testUpdateUser(){
		dbUser.setTile("Mrs");
		User foundUser  = userService.update(dbUser);
		assertThat(foundUser.getTile(), equalTo("Mrs"));
	}

}

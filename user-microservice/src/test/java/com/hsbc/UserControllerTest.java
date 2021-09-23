package com.hsbc;

import com.hsbc.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private TestRestTemplate rest;

    private ResponseEntity<User> baseUser;

    @BeforeEach
    public void setUp(){
        baseUser = create();
    }

    @AfterEach
    public void tearDown(){
        long id = baseUser.getBody().getId();
        rest.delete("/users/"+id);
    }

    @Test
    public void findUserById(){
        long id = baseUser.getBody().getId();
        ResponseEntity<User> userEntity = rest.getForEntity("/users/"+id, User.class);
        assertThat(userEntity.getBody().getFirstName(), equalTo("Homer"));
    }

    @Test
    public void findUserByFirstName(){
        String firstName = baseUser.getBody().getFirstName();
        ResponseEntity<List> userEntity = rest.getForEntity("/users/first-name/"+firstName, List.class);
        assertThat(userEntity.getBody().size(), equalTo(1));
    }

    @Test
    public void findUserByLastName(){
        String lastName = baseUser.getBody().getLastName();
        ResponseEntity<List> userEntity = rest.getForEntity("/users/last-name/"+lastName, List.class);
        assertThat(userEntity.getBody().size(), equalTo(1));
    }

    @Test
    public void updateUser(){
        String newName = "Marge";
        ResponseEntity<List> userEntity = rest.getForEntity("/users/first-name/"+newName, List.class);

        assertThat(userEntity.getBody().size(), equalTo(0));

        User user = baseUser.getBody();
        user.setFirstName(newName);
        rest.put("/users",  user);

        userEntity = rest.getForEntity("/users/first-name/"+newName, List.class);
        assertThat(userEntity.getBody().size(), equalTo(1));

    }

    private ResponseEntity create(){
        ResponseEntity<User> userEntity = rest.postForEntity("/users/", getUser(), User.class);
        return userEntity;
    }

    private User getUser(){
        User user = new User();
        user.setDob("1/1/2000");
        user.setFirstName("Homer");
        user.setLastName("Simpson");
        user.setTile("Mr");
        user.setJobTitle("Nuclear Technician");
        return user;
    }

}

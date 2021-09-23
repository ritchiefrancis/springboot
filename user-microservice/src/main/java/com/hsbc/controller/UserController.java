package com.hsbc.controller;

import com.hsbc.domain.User;
import com.hsbc.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Fetches all users with the given lastname", response = User.class, responseContainer = "List") })
	@GetMapping("last-name/{lastName}")
	public List<User> getUsersByLastName(@PathVariable String lastName){
			return service.findByLastName(lastName);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Fetches all users with the given firstname", response = User.class, responseContainer = "List") })
	@GetMapping("first-name/{firstName}")
	public List<User> getUsersByFirstName(@PathVariable String firstName){
		return service.findByFirstName(firstName);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Fetches the given user for the id passed", response = User.class) ,
			@ApiResponse(code = 404, message = "If no User for the given ID is found", response = User.class) })
	@GetMapping("{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		User user = service.findById(id);

		if(user == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Deletes the given user for the id passed")
	})
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		service.delete(id);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updates the given user", response = User.class)
	})
	@PutMapping()
	public User update(@Valid @RequestBody User user){
		return service.update(user);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Creates a new user", response = User.class)
	})
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user){
		return service.create(user);
	}

}


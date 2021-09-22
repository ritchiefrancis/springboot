package com.hsbc.service;

import com.hsbc.domain.User;

import java.util.List;

public interface UserService {

    void delete(Long id);

    User findById(Long id);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String LastName);

    User update(User user);

    User create(User user);
}

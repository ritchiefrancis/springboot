package com.hsbc.service;

import com.hsbc.domain.User;
import com.hsbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Transactional
    @Override
    public User update(User user) {
       return repository.save(user);
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }
}

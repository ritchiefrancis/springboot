package com.hsbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hsbc.domain.User;
import com.hsbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public void delete(Long id) {
        LOGGER.info(String.format("Deleting - id %d", id));
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        LOGGER.info(String.format("FindById  - id %d", id));
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        LOGGER.info(String.format("FindByFirstName  -  %s", firstName));
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        LOGGER.info(String.format("FindByLastName  -  %s", lastName));
        return repository.findByLastName(lastName);
    }

    @Transactional
    @Override
    public User update(User user) {
        LOGGER.info(String.format("Updating user   -  %d", user.getId()));
        return repository.save(user);
    }

    @Override
    public User create(User user) {
        LOGGER.info(String.format("Saving user "));
        return repository.save(user);
    }
}

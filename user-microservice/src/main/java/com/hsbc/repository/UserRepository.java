package com.hsbc.repository;

import com.hsbc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    void deleteById(Long id);
}

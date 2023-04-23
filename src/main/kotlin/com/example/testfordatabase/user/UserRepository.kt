package com.example.testfordatabase.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String username);

    Optional<User> findByUsername(String username);
}

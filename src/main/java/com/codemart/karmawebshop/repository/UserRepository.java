package com.codemart.karmawebshop.repository;

import com.codemart.karmawebshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends BaseRepository<User> {
    boolean existsByUsername(String username);
    User getUserByUsername(String username);

}

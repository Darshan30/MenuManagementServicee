package com.amrita.menu.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrita.menu.service.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  
  User findByEmail(String email);
  
  Optional<User> findByRollNo(String rollNo);

  Boolean existsByUsername(String username);
  
  Boolean existsByRollNo(String username);

  Boolean existsByEmail(String email);
}

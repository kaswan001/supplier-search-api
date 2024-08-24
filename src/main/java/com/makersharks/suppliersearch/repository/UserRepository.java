package com.makersharks.suppliersearch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makersharks.suppliersearch.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsernameOrEmail(String username, String email);
}

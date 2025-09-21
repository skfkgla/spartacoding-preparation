package com.sparta.jpaadvance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.jpaadvance.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);
}

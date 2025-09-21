package com.sparta.myselectshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.myselectshop.entity.ApiUseTime;
import com.sparta.myselectshop.entity.User;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {
	Optional<ApiUseTime> findByUser(User loginUser);
}

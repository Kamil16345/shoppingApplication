package com.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelmanagement.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

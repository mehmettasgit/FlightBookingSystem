package com.mehmet.FlightBookingSystem.security;

import com.mehmet.FlightBookingSystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteByUsername(String username);
}

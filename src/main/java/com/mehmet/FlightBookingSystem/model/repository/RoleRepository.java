package com.mehmet.FlightBookingSystem.model.repository;

import com.mehmet.FlightBookingSystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}


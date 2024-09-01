package com.corecoda.ikollect.repositories;

import com.corecoda.ikollect.entities.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, UUID> {
    SystemAdmin findByUsernameIgnoreCase(String username);
    SystemAdmin findByEmail(String email);
}

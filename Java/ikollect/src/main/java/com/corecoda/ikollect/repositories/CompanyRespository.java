package com.corecoda.ikollect.repositories;

import com.corecoda.ikollect.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRespository extends JpaRepository<Company, UUID> {
}


package com.corecoda.ikollect.repositories;

import com.corecoda.ikollect.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Query(value = """
      select t from Token t inner join ApplicationUser u\s
      on t.userId = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUserId(UUID id);

    Optional<Token> findByToken(String token);
}

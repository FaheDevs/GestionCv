package com.zidani.gestioncv.authenticationManagment.tokenManagement;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Hidden
public interface TokenRepository extends JpaRepository<Token, Long> {
/**
 * retrieve a list of valid tokens associated with a specific Person based on the provided id.
 * It ensures that the tokens are associated with the specified Person (u.id = :id) and are either not expired or not revoked.
 * */
  @Query(value = """
      select t from Token t inner join Person u\s
      on t.person.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Long id);

  Optional<Token> findByToken(String token);
}

package com.mmt.diagnosis.repository.users;

import com.mmt.diagnosis.domain.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @EntityGraph(attributePaths = "userAuthoritySet")
    Optional<Users> findOneWithAuthoritiesByUserEmail(String UserEmail);
}

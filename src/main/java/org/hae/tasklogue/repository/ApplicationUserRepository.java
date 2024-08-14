package org.hae.tasklogue.repository;

import org.hae.tasklogue.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findApplicationUserByUserNameOrEmail(String userName,String email);
}

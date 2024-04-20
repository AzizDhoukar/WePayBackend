package org.example.wepaybackend.appuser.repositorys;

import org.example.wepaybackend.appuser.models.AppUser;
import org.example.wepaybackend.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
    Integer findId(String email);
    AppUser findAppUserById(Integer id) throws UserNotFoundException;
}

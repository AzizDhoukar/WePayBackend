package org.example.wepaybackend.appuser.repositorys;

import org.example.wepaybackend.appuser.models.AppUser;
import org.example.wepaybackend.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
    @Query("SELECT id FROM AppUser WHERE email=?1")
    Integer findId(String email);
    AppUser  findAppUserByEmail(String username);
    AppUser findAppUserById(Integer id) throws UserNotFoundException;
}

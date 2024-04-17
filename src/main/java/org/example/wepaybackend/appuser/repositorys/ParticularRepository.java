package org.example.wepaybackend.appuser.repositorys;

import org.example.wepaybackend.appuser.models.ParticularUser;
import org.example.wepaybackend.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ParticularRepository extends JpaRepository<ParticularUser, Integer> {
    ParticularUser findAppUserById(Integer id) throws UserNotFoundException;

    ParticularUser findByEmail(String email);
}
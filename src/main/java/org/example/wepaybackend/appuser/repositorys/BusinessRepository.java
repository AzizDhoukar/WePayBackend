package org.example.wepaybackend.appuser.repositorys;

import org.example.wepaybackend.appuser.models.BusinessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BusinessRepository extends JpaRepository<BusinessUser, Integer> {
    BusinessUser findBusinessUserById(Integer id);
    BusinessUser findBusinessUserByAccountIdentifier(String accountIdentifier);

}

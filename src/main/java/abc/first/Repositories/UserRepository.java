package abc.first.Repositories;


import abc.first.Domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDomain, Long> {
    List<UserDomain> findByPhoneNumber(Long phoneNumber);
}

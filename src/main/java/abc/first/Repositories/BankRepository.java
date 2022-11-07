package abc.first.Repositories;


import abc.first.Domain.BankDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public interface BankRepository extends JpaRepository<BankDomain, Long> {

    List<BankDomain> findByUserId(Long userId);
//    @Query("delete from accounts where user_id:=user_id")
    void deleteByUserId( Long user_id);


}

package repositories;

import models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findAll();

//    @Query("SELECT c FROM OWNERS c ORDER BY c.dateBirth DESC")
//    List<Owner> sortByAge();
}

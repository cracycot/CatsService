package repositories;

import models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
        List<Cat> findAll();

//        @Query("SELECT c FROM CATS c ORDER BY c.dateBirth ASC")
//        List<Cat> sortByAge();
}

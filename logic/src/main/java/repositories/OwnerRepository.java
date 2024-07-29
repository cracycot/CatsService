package repositories;

import dao.OwnerDAO;
import models.Cat;
import models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

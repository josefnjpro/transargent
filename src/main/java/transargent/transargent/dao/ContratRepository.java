package transargent.transargent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transargent.transargent.model.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
}

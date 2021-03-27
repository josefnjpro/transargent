package transargent.transargent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transargent.transargent.model.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
}

package transargent.transargent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transargent.transargent.model.Caissier;
import transargent.transargent.model.Role;

@Repository
public interface CaissierRepository extends JpaRepository<Caissier, Long> {
    public Caissier findCaissierByCode(String code) ;
}

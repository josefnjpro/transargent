package transargent.transargent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transargent.transargent.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findClientByNci(long nci);
}

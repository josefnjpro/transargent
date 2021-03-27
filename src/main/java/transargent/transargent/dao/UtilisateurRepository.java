package transargent.transargent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transargent.transargent.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    public Utilisateur findByUsername(String username);
}

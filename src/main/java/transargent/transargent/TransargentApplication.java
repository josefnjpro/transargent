package transargent.transargent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import transargent.transargent.dao.RoleRepository;
import transargent.transargent.dao.UtilisateurRepository;
import transargent.transargent.model.Role;
import transargent.transargent.model.Utilisateur;

@SpringBootApplication
public class TransargentApplication implements CommandLineRunner {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    public static void main(String[] args) {
        SpringApplication.run(TransargentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*Role admin = new Role();
        Role caissier = new Role();
        admin.setLibelle("ROLE_ADMIN");
        caissier.setLibelle("ROLE_CAISSIER");
        roleRepository.save(admin);
        roleRepository.save(caissier);
        Utilisateur user = new Utilisateur();
        user.setEmail("smaster@smaster.app");
        user.setUsername("smaster");
        user.setNom("master");
        user.setPrenom("soul");
        user.setTelephone("770000000");
        user.setPassword(encoder.encode("passer"));
        utilisateurRepository.save(user);*/

        System.out.println(utilisateurRepository.findAll().size());
    }
}

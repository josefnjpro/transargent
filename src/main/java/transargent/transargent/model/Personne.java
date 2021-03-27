package transargent.transargent.model;

import javax.persistence.*;

@Entity
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long nci;
    @Column(length = 50)
    private String nom;
    @Column(length = 50)
    private String prenom;
    @Column(length = 20)
    private String telephone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNci() {
        return nci;
    }

    public void setNci(long nci) {
        this.nci = nci;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

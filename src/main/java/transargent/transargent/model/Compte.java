package transargent.transargent.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long solde;
    @Column(length = 50)
    private String type;

    /*@OneToOne
    @JoinColumn(name = "caissier_id")
    private Caissier caissier;*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSolde() {
        return solde;
    }

    public void setSolde(long solde) {
        this.solde = solde;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*public Caissier getCaissier() {
        return caissier;
    }

    public void setCaissier(Caissier caissier) {
        this.caissier = caissier;
    }*/
}

package transargent.transargent.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Compte compte;

    @OneToMany(mappedBy = "client")
    private List<Transaction> transactions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

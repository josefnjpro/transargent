package transargent.transargent.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String numcontrat;
    @Column(length = 50)
    private String type;

    /*@OneToOne
    private Caissier caissier;*/

    @OneToMany(mappedBy = "contrat")
    private List<Article> articles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumcontrat() {
        return numcontrat;
    }

    public void setNumcontrat(String numcontrat) {
        this.numcontrat = numcontrat;
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


    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}

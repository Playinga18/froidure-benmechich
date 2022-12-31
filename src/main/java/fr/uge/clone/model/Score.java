package fr.uge.clone.model;

import javax.persistence.*;

/**
 * Classe représentant un score.
 * Un objet de cette classe contient les informations suivantes sur un score :
 *   son identifiant unique
 *   sa valeur
 */
@Entity
@Table(name = "Score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idS;

    @Column(name = "score")
    private double score;

    /**
     * Construit un nouvel objet Score vide.
     */
    public Score(){}

    /**
     * Construit un nouvel objet Score à partir des informations fournies.
     *
     * @param id l'identifiant unique du score
     * @param score la valeur du score
     */
    public Score(long id, long score){
        this.score = score;
    }

    /**
     * Retourne l'identifiant unique du score.
     *
     * @return l'identifiant unique du score
     */
    public long getId() {
        return idS;
    }

    /**
     * Modifie l'identifiant unique du score.
     *
     * @param id le nouvel identifiant unique du score
     */
    public void setId(long id) {
        this.idS = id;
    }

    /**
     * Retourne la valeur du score.
     *
     * @return la valeur du score
     */
    public double getNumVersion() {
        return score;
    }

    /**
     * Modifie la valeur du score.
     *
     * @param score la nouvelle valeur du score
     */
    public void setNumVersion(double score) {
        this.score = score;
    }

}

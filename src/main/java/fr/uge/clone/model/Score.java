package fr.uge.clone.model;

import javax.persistence.*;

@Entity
@Table(name = "Score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idS;

    @Column(name = "score")
    private double score;

    public Score(){}

    public Score(long id, long score){
        this.score = score;
    }

    public long getId() {
        return idS;
    }

    public void setId(long id) {
        this.idS = id;
    }

    public double getNumVersion() {
        return score;
    }

    public void setNumVersion(double score) {
        this.score = score;
    }

}

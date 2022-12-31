package fr.uge.clone.backend;

import java.util.List;

public record Score(int idA1, int idA2, double score, List<Hash> hash) {

    public Score(int idA1, int idA2, double score, List<Hash> hash) {
        this.idA1 = idA1;
        this.idA2 = idA2;
        this.score = score;
        this.hash = hash;
    }
}

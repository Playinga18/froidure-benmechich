package fr.uge.clone.backend;

import java.util.Objects;

public record Score(int idA1, int idA2, double score) {

    public Score(int idA1, int idA2, double score) {
        Objects.requireNonNull(idA1);
        Objects.requireNonNull(idA2);
        Objects.requireNonNull(score);
        this.idA1 = idA1;
        this.idA2 = idA2;
        this.score = score;
    }
}

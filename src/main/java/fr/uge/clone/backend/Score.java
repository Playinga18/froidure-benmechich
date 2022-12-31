package fr.uge.clone.backend;

import java.util.List;

public record Score(long idA, double score, List<Hash> hash) {}

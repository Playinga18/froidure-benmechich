package fr.uge.clone.backend;

import java.util.List;

/**
 * Représente le score de plagiat entre deux artefact.
 */
public record Score(long idA, double score, List<Hash> hash) {}

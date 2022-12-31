package fr.uge.clone.backend;

import java.util.Objects;

/**
 * Représente le hash dun bloc de bytecode.
 */
public class Hash {
    private final int line;
    private final long hash;
    private final static int CONSTANT_A = 20;
    private final static int CONSTANT_B = 1017;

    /**
     * Construit un nouvel hash à partir du contenu des lignes et de sa position dans le fichier.
     *
     * @param source Le contenu des ligne sous forme de tableau de chaînes de caractères.
     * @param line   La position de la ligne dans le fichier.
     * @throws NullPointerException     Si le contenu est null.
     * @throws IllegalArgumentException Si la position de la ligne est négative.
     */
    public Hash(String[] source, int line) {
        Objects.requireNonNull(source);
        if (line < 0) {
            throw new IllegalArgumentException("line must be positive");
        }
        this.line = line;
        hash = hashing(source);
    }

    /**
     * Calcule le hash de la ligne à partir de son contenu.
     *
     * @param source Le contenu de la ligne sous forme de tableau de chaînes de caractères.
     * @return Le hash du bloc.
     */
    private long hashing(String[] source) {
        double result = 0;
        for (var i = 0; i < source.length; i++) {
            if (source[i] != null) {
                result += HashingInst(source[i], i);
            }
        }
        return (long) result;
    }

    /**
     * Calcule le hash de l'instruction à partir de son contenu et de sa position dans la ligne.
     *
     * @param inst Le contenu de l'instruction sous forme de chaîne de caractères.
     * @param n    La position de l'instruction dans la ligne.
     * @return L'index de l'instruction.
     */
    private long HashingInst(String inst, int n) {
        return Math.floorMod(inst.hashCode() * (long) Math.pow(CONSTANT_A, n), CONSTANT_B);
    }

    /**
     * Calcule le hash de la ligne suivante à partir du hash de la ligne courante.
     *
     * @param source Le contenu de la ligne suivante sous forme de tableau de chaînes de caractères.
     * @param line   La position de la ligne suivante dans le fichier.
     * @return Le hash de la ligne suivante.
     */
    public Hash addRolling(String[] source, int line) {
        return new Hash(source, this.line + line);
    }

    /**
     * Retourne le hash de la ligne.
     *
     * @return L'index de la ligne.
     */
    public long getHash() {
        return this.hash;
    }

    /**
     * Retourne une représentation de chaîne de caractères de cet hash.
     *
     * @return La représentation de chaîne de caractères de cet index.
     */
    @Override
    public String toString() {
        return "hash: " + hash + " from: " + line + " line";
    }

    /**
     * Retourne la position de la ligne dans le fichier.
     *
     * @return La position de la ligne dans le fichier.
     */
    public int getLine() {
        return this.line;
    }
}

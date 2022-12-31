package fr.uge.clone.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Représente un bloc de bytecode.
 */
public class BytecodeBlock {
    private final String[] block;
    private final static int BLOCK_LENGTH = 10;

    /**
     * Construit un nouveau bloc de bytecode avec le contenu donné et la ligne de début.
     *
     * @param line La ligne de début du bloc.
     * @param block Le contenu du bloc sous forme de chaîne de caractères.
     * @throws NullPointerException Si le contenu est null.
     * @throws IllegalArgumentException Si la ligne de début est négative.
     */
    public BytecodeBlock(int line, String block) {
        Objects.requireNonNull(block);
        if (line < 0){
            throw new IllegalArgumentException("line must be positive");
        }
        this.block = block.split("\n");
    }

    /**
     * Retourne la longueur des blocs.
     *
     * @return La longueur des blocs.
     */
    public static int getBlockLength() {
        return BLOCK_LENGTH;
    }

    /**
     * Calcule la liste des hash de chaque du bloc de ligne du bytecode.
     *
     * @return La liste des hash.
     */
    public ArrayList<Hash> BlockToHash() {
        var lstHash = new ArrayList<Hash>();
        for (var i = 0; i <= block.length/BLOCK_LENGTH; i++) {
            var tmp = Arrays.copyOfRange(block, i*BLOCK_LENGTH, (i+1)*BLOCK_LENGTH);
            var hash = new Hash(tmp, i*BLOCK_LENGTH);
            if (hash.getHash() > 0){
                lstHash.add(hash);
            }
        }
        return lstHash;
    }

    /**
     * Calcule le score de chaque ligne du bloc.
     *
     * @return La liste des scores.
     */
    public ArrayList<Score> KP() {
        var kp = new KarpRabin(block);
        return kp.KarpRabin();
    }

    /**
     * Retourne une représentation de chaîne de caractères de ce bloc.
     *
     * @return La représentation de chaîne de caractères de ce bloc.
     */
    @Override
    public String toString() {
        var str = new StringBuilder();
        for (var i = 0; i < block.length; i++) {
            str.append(i).append(" : ").append(block[i]).append("\n");
        }
        return str.toString();
    }
}

package fr.uge.clone.backend;

import fr.uge.clone.service.ArtefactService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BytecodeBlock {
    private final String[] block;
    private final static int BLOCK_LENGTH = 10;

    public BytecodeBlock(int line, String block) {
        Objects.requireNonNull(block);
        if (line < 0){
            throw new IllegalArgumentException("line must be positive");
        }
        this.block = block.split("\n");
    }

    public static int getBlockLength(){ return BLOCK_LENGTH; }

    public ArrayList<Hash> BlockToHash(){
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

    public ArrayList<Score> KP(){
        var kp = new KarpRabin(block);
        return kp.KarpRabin();
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        for (var i = 0; i < block.length; i++) {
           str.append(i).append(" : ").append(block[i]).append("\n");
        }
        return str.toString();
    }
}

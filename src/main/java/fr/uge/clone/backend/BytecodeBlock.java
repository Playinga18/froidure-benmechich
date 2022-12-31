package fr.uge.clone.backend;

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

    private Score KPbyArtefact(int idA1, int idA2, ArrayList<Hash> list){
        var rHash = new Hash(Arrays.copyOfRange(block, 0, BLOCK_LENGTH),0 );
        var list_sim = new ArrayList<Hash>();
        for (var i = BLOCK_LENGTH; i < list.size(); i++){
            if (list.contains(rHash.getHash())){
                list_sim.add(rHash);
                i += BLOCK_LENGTH;
            }
            rHash.addRolling(Arrays.copyOfRange(block, i - BLOCK_LENGTH, i), i-BLOCK_LENGTH);
        }
        return new Score(idA1,idA2, list_sim.size()/list.size(), list_sim);
    }

    public ArrayList<Score>KarpRabin(){
        // -> futur map
        ArrayList<Score> scores = new ArrayList<Score>();
        for(var i = 0; i < 10;i++){
            var tmp = KPbyArtefact(0,1,BlockToHash());
            if (tmp.score() > 0){
                scores.add( KPbyArtefact(0,1,BlockToHash()));
            }
        }
        return scores;
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        for (var i = 0; i < block.length; i++) {
           str.append(i).append(" : ").append(block[i]).append("\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        var str = """
                class AnnotationVisitor
                I api
                AnnotationVisitor av
                <init>
                ALOAD 0
                ILOAD 1
                ACONST_NULL
                INVOKESPECIAL <init>
                RETURN
                LOCALVARIABLE this AnnotationVisitor   0
                LOCALVARIABLE api I   1
                """;
        var block = new BytecodeBlock(0, str);
        System.out.println(block);
        System.out.println(block.BlockToHash());
    }
}

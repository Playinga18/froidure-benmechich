package fr.uge.clone.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BytecodeBlock {
    private String[] block;
    private final static int BLOCK_LENGTH = 3;

    public BytecodeBlock(int line, String block) {
        Objects.requireNonNull(block);
        if (line < 0){
            throw new IllegalArgumentException("line must be positive");
        }
        this.block = block.split("\n");
    }

    public Hash addRolling(Hash h){
        Objects.requireNonNull(h);
        var line = h.getLine();
        if (line + 1 < block.length){
            var source = Arrays.copyOfRange(block, h.getLine() + 1, h.getLine() + 1 + BLOCK_LENGTH);
            return h.addRolling(source);
        }
        return null;
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

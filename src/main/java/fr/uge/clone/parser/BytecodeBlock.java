package fr.uge.clone.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BytecodeBlock {
    private int line;
    private String[] block;
    private final static int BLOCK_LENGTH = 5;

    public BytecodeBlock(int line, String block) {
        Objects.requireNonNull(block);
        if (line < 0){
            throw new IllegalArgumentException("line must be positive");
        }
        this.line = line;
        this.block = block.split("\n");
    }

    public void addRolling(String newInst){
        Objects.requireNonNull(newInst);
        var tmp = new String[block.length];
        for (var i = 0; i < block.length-1; i++){
            tmp[i] = block[i+1];
        }
        tmp[block.length-1] = newInst;
        line += 1;
        block = tmp;
    }

    public ArrayList<Hash> BlockToHash(){
        var lstHash = new ArrayList<Hash>();
        for (var i = 0; i <= block.length/BLOCK_LENGTH; i++) {
            var tmp = Arrays.copyOfRange(block, i*BLOCK_LENGTH, (i+1)*BLOCK_LENGTH);
            System.out.println(Arrays.toString(tmp));
            lstHash.add( new Hash(tmp, i*BLOCK_LENGTH));
        }
        return lstHash;
    }

    public int line(){
        return this.line;
    }

    public String[] getBlock(){
        return block;
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        for (var i = 0; i < block.length; i++) {
           str.append(i + " : "+ block[i] + "\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        var block = new BytecodeBlock(0, "Hello\nBonjour\nca\nva\nhier\ndemain\nciao\nle\nla");
        System.out.println(block);
        System.out.println(block.BlockToHash());
    }
}

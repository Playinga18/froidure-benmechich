package fr.uge.clone.parser;

import java.util.Objects;

public class Hash {
    private int line;
    private long hash;
    private String[] source;
    private final static int CONSTANT_A = 10;
    private final static int CONSTANT_B = 301;

    public Hash(String[] source, int line) {
        Objects.requireNonNull(source);
        if (line < 0){
            throw new IllegalArgumentException("line must be positive");
        }
        this.line = line;
        this.source = source;
        hash = hashing();
    }

    private long hashing () {
        double result = 0;
        for (var i = 0; i < source.length; i++){
            if (source[i] != null){
                result += HashingInst(source[i],i );
            }
        }
        return (long) result;
    }

    private long HashingInst(String inst, int n){
        return (long) (inst.hashCode() * Math.pow(CONSTANT_A, source.length - n) % CONSTANT_B);
    }

    public void addRolling(String newInst){
        Objects.requireNonNull(newInst);
        var tmp = new String[source.length];
        if (source.length - 1 >= 0) System.arraycopy(source, 1, tmp, 0, source.length - 1);
        tmp[source.length-1] = newInst;
        line += 1;
        source = tmp;
        hash = hashing();
    }

    public long getHash(){
        return this.hash;
    }

    @Override
    public String toString() {
        return "hash: " + hash + " from: " + line + " line";
    }

    public static void main(String[] args) {
        var hash = new Hash("Hello Bonjour ca va hier demain".split(" "), 1);
        System.out.println(hash.getHash());
        hash.addRolling("Comment");
        System.out.println(hash.getHash());
    }

}

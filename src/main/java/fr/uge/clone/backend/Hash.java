package fr.uge.clone.backend;

import java.util.Objects;

public class Hash {
    private final int line;
    private final long hash;
    private final static int CONSTANT_A = 20;
    private final static int CONSTANT_B = 1017;

    public Hash(String[] source, int line) {
        Objects.requireNonNull(source);
        if (line < 0){
            throw new IllegalArgumentException("line must be positive");
        }
        this.line = line;
        hash = hashing(source);
    }

    private long hashing (String[] source) {
        double result = 0;
        for (var i = 0; i < source.length; i++){
            if (source[i] != null){
                result += HashingInst(source[i],i );
            }
        }
        return (long) result;
    }

    private long HashingInst(String inst, int n){
        return Math.floorMod( inst.hashCode() * (long) Math.pow(CONSTANT_A, n), CONSTANT_B);
    }

    public Hash addRolling(String[] source, int line){
        return new Hash(source, this.line + line);
    }

    public long getHash(){
        return this.hash;
    }

    @Override
    public String toString() {
        return "hash: " + hash + " from: " + line + " line";
    }

    public static void main(String[] args) {
        var str = """
                LOCALVARIABLE this AnnotationVisitor 0
                LOCALVARIABLE this AnnotationVisitor 0
                LOCALVARIABLE this AnnotationVisitor 0
                LOCALVARIABLE this AnnotationVisitor 0
                LOCALVARIABLE this AnnotationVisitor 0
                """;
        var hash = new Hash(new String[]{str},0);
        System.out.println(hash.getHash());
    }

    public int getLine() { return this.line; }
}

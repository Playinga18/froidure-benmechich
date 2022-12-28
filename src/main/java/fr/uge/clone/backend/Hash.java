package fr.uge.clone.backend;

import java.util.Objects;

public class Hash {
    private final int line;
    private final long hash;
    private final static int CONSTANT_A = 10;
    private final static int CONSTANT_B = 301;

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
        var res = 0;
        for (var i = 0; i < inst.length(); i++){
            res += (inst.charAt(i));
        }
        return (long) (res * Math.pow(CONSTANT_A,BytecodeBlock.getBlockLength() - n) % CONSTANT_B);
    }

    public Hash addRolling(String[] source){
        return new Hash(source, line + 1);
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

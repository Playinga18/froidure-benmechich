package fr.uge.clone.parser;

public class Hash {
    private int line;
    private long hash;
    private String[] source;
    private final static int CONSTANT_A = 10;
    private final static int CONSTANT_B = 301;

    public Hash(String[] source, int line) {
        this.line = line;
        this.source = source;
        hash = hashing();
    }

    private long hashing () {
        double result = 0;
        for (var i = 0; i < source.length; i++){
            result += HashingInst(source[i],i );
        }
        return (long) result;
    }

    private long HashingInst(String inst, int n){
        return (long) (inst.hashCode() * Math.pow(CONSTANT_A, source.length - n) % CONSTANT_B);
    }

    public void addRolling(String newInst){
        var tmp = new String[source.length];
        for (var i = 0; i < source.length-1; i++){
            tmp[i] = source[i+1];
        }
        tmp[source.length-1] = newInst;
        line += 1;
        source = tmp;
        hash = hashing();
    }

    public long getHash(){
        return this.hash;
    }

    public int getLine(){
        return line;
    }

    public String[] getSources(){
        return source;
    }

    public static void main(String[] args) {
        var hash = new Hash("Hello Bonjour ca va".split(" "), 1);
        System.out.println(hash.getHash());
        hash.addRolling("Comment");
        System.out.println(hash.getHash());
    }


}

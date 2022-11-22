package fr.uge.clone.parser;

public class KarpRabin {
    private final static int A = 10;
    private final static int B = 317;

    private int Hash(String str, int n){
        return (str.hashCode() * A) % B;
    }
}

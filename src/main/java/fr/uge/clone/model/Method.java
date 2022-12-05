package fr.uge.clone.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Method")
public class Method {

    @Id
    private int hash;

    @Column(name = "nameFile")
    private String nameFile;

    @Column(name = "numLine")
    private String numLine;

    @Column(name = "methodSize")
    private long methodSize;

    @ManyToMany
    List<Artefact> artefacts;

    public Method() {

    }
    public Method(int hash, String nameFile, String numLine, long methodSize) {
        this.hash = hash;
        this.nameFile = nameFile;
        this.numLine = numLine;
        this.methodSize = methodSize;
    }


    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getNumLine() {
        return numLine;
    }

    public void setNumLine(String numLine) {
        this.numLine = numLine;
    }

    public long getMethodSize() {
        return methodSize;
    }

    public void setMethodSize(long methodSize) {
        this.methodSize = methodSize;
    }
}

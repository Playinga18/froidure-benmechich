package fr.uge.clone.model;

import javax.persistence.*;
import java.util.List;

/**
 * Classe représentant un bloc de code.
 *
 * Un objet de cette classe contient les informations suivantes sur un bloc de code :
 *   son hash unique
 *   le nom du fichier dans lequel il se trouve
 *   le numéro de ligne où il commence
 *   sa taille en nombre de lignes
 *   la liste des artefacts dans lesquels il est présent
 */
@Entity
@Table(name = "Block")
public class Block {

    @Id
    private long hash;

    @Column(name = "nameFile")
    private String nameFile;

    @Column(name = "numLine")
    private String numLine;

    @Column(name = "methodSize")
    private long methodSize;

    @ManyToMany
    List<Artefact> artefacts;

    /**
     * Construit un nouvel objet Block vide.
     */
    public Block() {
    }

    /**
     * Construit un nouvel objet Block à partir des informations fournies.
     *
     * @param hash l'hash unique du bloc de code
     * @param nameFile le nom du fichier dans lequel se trouve le bloc de code
     * @param numLine le numéro de ligne où commence le bloc de code
     * @param methodSize la taille du bloc de code en nombre de lignes
     */
    public Block(long hash, String nameFile, String numLine, long methodSize) {
        this.hash = hash;
        this.nameFile = nameFile;
        this.numLine = numLine;
        this.methodSize = methodSize;
    }


    /**
     * Retourne l'hash unique du bloc de code.
     *
     * @return l'hash unique du bloc de code
     */
    public long getHash() {
        return hash;
    }

    /**
     * Modifie l'hash unique du bloc de code.
     *
     * @param hash le nouvel hash unique du bloc de code
     */
    public void setHash(long hash) {
        this.hash = hash;
    }

    /**
     * Retourne le nom du fichier dans lequel se trouve le bloc de code.
     *
     * @return le nom du fichier dans lequel se trouve le bloc de code
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * Modifie le nom du fichier dans lequel se trouve le bloc de code.
     *
     * @param nameFile le nouveau nom du fichier dans lequel se trouve le bloc de code
     */
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    /**
     * Retourne le numéro de ligne où commence le bloc de code.
     *
     * @return le numéro de ligne où commence le bloc de code
     */
    public String getNumLine() {
        return numLine;
    }

    /**
     * Modifie le numéro de ligne où commence le bloc de code.
     *
     * @param numLine le nouveau numéro de ligne où commence le bloc de code
     */
    public void setNumLine(String numLine) {
        this.numLine = numLine;
    }

    /**
     * Retourne la taille du bloc de code en nombre de lignes.
     *
     * @return la taille du bloc de code en nombre de lignes
     */
    public long getMethodSize() {
        return methodSize;
    }

    /**
     * Modifie la taille du bloc de code en nombre de lignes.
     *
     * @param methodSize la nouvelle taille du bloc de code en nombre de lignes
     */
    public void setMethodSize(long methodSize) {
        this.methodSize = methodSize;
    }




}

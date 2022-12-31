package fr.uge.clone.model;


import javax.persistence.*;
import java.util.List;

/**
 * Classe représentant un artefact.

 * Un artefact est un élément de code qui est produit lors du développement d'un logiciel. Il peut s'agir par exemple d'un fichier source, d'un exécutable, d'une documentation, etc.
 * Un objet de cette classe contient les informations suivantes sur un artefact :
 *   son identifiant unique
 *   sa version
 *   le nom du développeur qui l'a produit
 *   son chemin d'accès
 *   ses métadonnées
 *   sa description
 */
@Entity
@Table(name = "artefacts")
public class Artefact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String numVersion;

    private String developer;

    private String path;

    @Lob
    private String metaData;

    @Lob
    private String description;

    @ManyToMany
    List<Block> blocks;

    /**
     * Construit un nouvel objet Artefact vide.
     */
    public Artefact() {
    }

    /**
     * Construit un nouvel objet Artefact à partir des informations fournies.
     *
     * @param numVersion la version de l'artefact
     * @param developer le nom du développeur qui a produit l'artefact
     * @param path le chemin d'accès de l'artefact
     * @param metaData les métadonnées de l'artefact
     * @param description la description de l'artefact
     */
    public Artefact(String numVersion, String developer, String path, String metaData, String description) {
        this.numVersion = numVersion;
        this.developer = developer;
        this.path = path;
        this.metaData = metaData;
        this.description = description;
    }

    /**
     * Retourne l'identifiant unique de l'artefact.
     *
     * @return l'identifiant unique de l'artefact
     */
    public long getId() {
        return id;
    }

    /**
     * Modifie l'identifiant unique de l'artefact.
     *
     * @param id le nouvel identifiant unique de l'artefact
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retourne la version de l'artefact.
     *
     * @return la version de l'artefact
     */
    public String getNumVersion() {
        return numVersion;
    }

    /**
     * Modifie la version de l'artefact.
     *
     * @param numVersion la nouvelle version de l'artefact
     */
    public void setNumVersion(String numVersion) {
        this.numVersion = numVersion;
    }

    /**
     * Retourne le nom du développeur qui a produit l'artefact.
     *
     * @return le nom du développeur qui a produit l'artefact
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Modifie le nom du développeur qui a produit l'artefact.
     *
     * @param developer le nouveau nom du développeur qui a produit l'artefact
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Retourne le chemin d'accès de l'artefact.
     *
     * @return le chemin d'accès de l'artefact
     */
    public String getPath() {
        return path;
    }

    /**
     * Modifie le chemin d'accès de l'artefact.
     *
     * @param path le nouveau chemin d'accès de l'artefact
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Retourne les métadonnées de l'artefact.
     *
     * @return les métadonnées de l'artefact
     */
    public String getMetaData() {
        return metaData;
    }

    /**
     * Modifie les métadonnées de l'artefact.
     *
     * @param metaData les nouvelles métadonnées de l'artefact
     */
    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    /**
     * Retourne la description de l'artefact.
     *
     * @return la description de l'artefact
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de l'artefact.
     *
     * @param description la nouvelle description de l'artefact
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la liste des blocs de l'artefact.
     *
     * @return la liste des blocs de l'artefact
     */
    public List<Block> getBlocks(){
        return blocks;
    }

    /**
     * Retourne une chaîne de caractères représentant l'artefact.
     *
     * La chaîne retournée est de la forme
     *  id : identifiant unique de l'artefact
     *  numVersion : version de l'artefact
     *  developer : nom du développeur qui a produit l'artefact
     *  path : chemin d'accès de l'artefact
     *  metaData : métadonnées de l'artefact
     *  description : description de l'artefact
     *
     * @return une chaîne de caractères représentant l'artefact
     */
    @Override
    public String toString(){
        return "id : " + id + "\n numVersion : " + numVersion + "\n developer : " + developer + "\n path : " + path + "\n metaData : " + metaData + "\n description : " + description;
    }

}

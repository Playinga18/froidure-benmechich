package fr.uge.clone.model;


import javax.persistence.*;
import java.util.List;

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

    public Artefact() {
    }

    public Artefact(String numVersion, String developer, String path, String metaData, String description) {
        this.numVersion = numVersion;
        this.developer = developer;
        this.path = path;
        this.metaData = metaData;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(String numVersion) {
        this.numVersion = numVersion;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Block> getBlocks(){
        return blocks;
    }

    @Override
    public String toString(){
        return "id : " + id + "\n numVersion : " + numVersion + "\n developer : " + developer + "\n path : " + path + "\n metaData : " + metaData + "\n description : " + description;
    }

}

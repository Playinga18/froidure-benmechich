package fr.uge.clone.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artefacts")
public class Artefact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idA;

    @Column(name = "numVersion")
    private long numVersion;

    @Column(name = "developer")
    private String developer;

    @Column(name = "path")
    private String path;

    @Column(name = "metaData")
    private String metaData;

    @Column(name = "description")
    private String description;

    @ManyToMany
    List<Method> methods;

    public Artefact() {
    }

    public Artefact(long id, long numVersion, String developer, String path, String metaData, String description) {
        this.numVersion = numVersion;
        this.developer = developer;
        this.path = path;
        this.metaData = metaData;
        this.description = description;
    }

    public long getId() {
        return idA;
    }

    public void setId(long id) {
        this.idA = id;
    }

    public long getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(long numVersion) {
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

    @Override
    public String toString(){
        return "id : " + id + "\n numVersion : " + numVersion + "\n developer : " + developer + "\n path : " + path + "\n metaData : " + metaData + "\n description : " + description;
    }

}

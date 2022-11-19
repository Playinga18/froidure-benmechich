package fr.uge.clone.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "artefacts")
public class Artefact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    public Artefact(String name){
        Objects.requireNonNull(name, "name is null");
        this.name = name;
    }

    public Artefact() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Artefact [id=" + id + ", name=" +  name + "]";
    }

}


package fr.uge.clone.controller;

import javax.persistence.*;

@Entity
@Table
public class Artefact {
    @Id
    @GeneratedValue
    private Long id;

    public Artefact(){
    }
    public Long getId() {
        return id;
    }



}

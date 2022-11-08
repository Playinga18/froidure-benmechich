
package fr.uge.clone;

import javax.persistence.*;

@Entity
@Table
public class Artefact {
    @Id
    @GeneratedValue
    private long id;
    public Long getId() {
        return id;
    }



}

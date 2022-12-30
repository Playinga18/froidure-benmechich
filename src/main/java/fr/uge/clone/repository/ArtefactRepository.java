package fr.uge.clone.repository;

import fr.uge.clone.model.Artefact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArtefactRepository extends JpaRepository<Artefact, Long> {

}

package fr.uge.clone.repository;

import fr.uge.clone.model.Artefact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CloneRepository extends JpaRepository<Artefact, Long> {

}

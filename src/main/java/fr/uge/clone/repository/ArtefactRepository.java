package fr.uge.clone.repository;

import fr.uge.clone.model.Artefact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtefactRepository extends JpaRepository<Artefact, Long> {

}

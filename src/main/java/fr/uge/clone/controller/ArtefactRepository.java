package fr.uge.clone.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtefactRepository<T> extends CrudRepository<Artefact, Long> {

}

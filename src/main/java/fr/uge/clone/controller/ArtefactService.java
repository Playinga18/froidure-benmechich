package fr.uge.clone.controller;

import fr.uge.clone.controller.Artefact;
import fr.uge.clone.controller.ArtefactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArtefactService{

    @Autowired
    ArtefactRepository<Artefact> repository;

    @Transactional
    public List<Artefact> getAllArtefacts(){
        return (List<Artefact>) repository.findAll();
    }

    @Transactional
    public void addArtefact(Artefact artefact){
        repository.save(artefact);
    }
}

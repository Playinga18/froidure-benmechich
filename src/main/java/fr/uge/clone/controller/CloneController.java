package fr.uge.clone.controller;

import fr.uge.clone.model.Artefact;
import fr.uge.clone.repository.CloneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CloneController {

    @Autowired
    private CloneRepository repository;

    @RequestMapping(value = "/addArtefact", method = RequestMethod.POST)
    public void insertNewArtefact() {
    }

    @RequestMapping(value = "/allArtefacts", method = RequestMethod.GET)
    public void getAllArtefacts() {
    }
}

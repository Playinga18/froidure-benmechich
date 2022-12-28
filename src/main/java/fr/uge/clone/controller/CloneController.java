package fr.uge.clone.controller;

import fr.uge.clone.model.Artefact;
import fr.uge.clone.repository.CloneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CloneController {

    @Autowired
    private CloneRepository repository;

    @RequestMapping(value = "/addArtefact", method = RequestMethod.GET)
    public void insertNewArtefact() {
       var tmp =  new Artefact(1L,20L, "yass", "test/test", "metatest", "descriptionTest");
       repository.save(tmp);
    }

    @RequestMapping(value = "/allArtefacts", method = RequestMethod.GET)
    public void getAllArtefacts() {
        var tmp = repository.findAll();
        System.out.println(tmp);
    }
}

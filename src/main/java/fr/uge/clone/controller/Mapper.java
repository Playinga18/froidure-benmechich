package fr.uge.clone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mapper {
    @Autowired ArtefactService service;
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/bdd")
    public String DbTest() {
        service.addArtefact(
                new Artefact()
        );
        service.addArtefact(
                new Artefact()
        );
        var res = service.getAllArtefacts();
        System.out.println("res : "+ res);
        return "home";
    }
}

package fr.uge.clone.service;

import fr.uge.clone.model.Artefact;
import fr.uge.clone.repository.ArtefactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ArtefactService {
    private final ArtefactRepository cloneRepo;

    @Autowired
    public ArtefactService(ArtefactRepository cloneRepo) {
        this.cloneRepo = cloneRepo;
    }


    public Artefact store(MultipartFile file) throws IOException {
        var filename = StringUtils.cleanPath(file.getOriginalFilename());
        var artefact = new Artefact(file.getOriginalFilename(), "test", file.getName(), file.toString(), "test");

        return cloneRepo.save(artefact);
    }

    public List<Artefact> findAllArtefact(){
        return cloneRepo.findAll();
    }

    public Stream<Artefact> getAllFiles() {
        return cloneRepo.findAll().stream();
    }

    public Artefact getFile(Long id) {
        return cloneRepo.findById(id).orElse(null);
    }
}

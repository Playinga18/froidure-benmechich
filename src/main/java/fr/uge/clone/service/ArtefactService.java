package fr.uge.clone.service;

import fr.uge.clone.model.Artefact;
import fr.uge.clone.repository.ArtefactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArtefactService {
    private final Object lock = new Object();
    private final ArtefactRepository cloneRepo;

    @Autowired
    public ArtefactService(ArtefactRepository cloneRepo) {
        this.cloneRepo = cloneRepo;
    }

    public Artefact store(MultipartFile file) throws IOException {
        synchronized (lock){
            var filename = StringUtils.cleanPath(file.getOriginalFilename());
            var artefact = new Artefact("version", "test", file.getOriginalFilename(), file.toString(), "test");

            return cloneRepo.save(artefact);
        }
    }

    public List<Artefact> findAllArtefact(){
        return cloneRepo.findAll();
    }

    public Stream<Artefact> getAllFiles() {
        return cloneRepo.findAll().stream();
    }

    public List<String> getIdOfFile(){
        var tmp = findAllArtefact();
        return tmp.stream().map(p -> p.getNumVersion()).toList();
    }

    public Artefact getFile(Long id) {
        return cloneRepo.findById(id).orElse(null);
    }

    public Map<Long, List<Long>> mapListOfHashByIdArtefact() {
        return cloneRepo.findAll().stream()
                .collect(Collectors.toMap(
                        artefact -> artefact.getId(),
                        artefact -> artefact.getBlocks()
                                .stream().map(p -> p.getHash()).collect(Collectors.toList()))
                );
    }

}

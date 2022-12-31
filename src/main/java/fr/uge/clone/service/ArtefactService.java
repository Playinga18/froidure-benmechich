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

/**
 * Classe de service pour la gestion des artefacts.
 * Cette classe fournit des méthodes pour :
 *   stocker un artefact
 *   récupérer tous les artefacts
 *   récupérer un artefact particulier
 *   mapper les listes d'hashes de chaque artefact par leur identifiant unique
 */
@Service
public class ArtefactService {
    private final ArtefactRepository cloneRepo;

    /**
     * Construit un nouvel objet ArtefactService avec le repository fourni.
     *
     * @param cloneRepo le repository pour accéder aux données des artefacts en base de données
     */
    @Autowired
    public ArtefactService(ArtefactRepository cloneRepo) {
        this.cloneRepo = cloneRepo;
    }

    /**
     * Stocke un artefact dans la base de données.
     *
     * @param file le fichier artefact à stocker
     * @return l'artefact créé et stocké en base de données
     * @throws IOException si une erreur survient lors de la lecture du fichier
     */
    public Artefact store(MultipartFile file) throws IOException {
        var filename = StringUtils.cleanPath(file.getOriginalFilename());
        var artefact = new Artefact(file.getOriginalFilename(), "test", file.getName(), file.toString(), "test");

        return cloneRepo.save(artefact);
    }

    /**
     * Récupère tous les artefacts stockés en base de données.
     *
     * @return la liste de tous les artefacts stockés en base de données
     */
    public List<Artefact> findAllArtefact(){
        return cloneRepo.findAll();
    }

    /**
     * Récupère tous les artefacts stockés en base de données sous forme de stream.
     *
     * @return le stream de tous les artefacts stockés en base de données
     */
    public Stream<Artefact> getAllFiles() {
        return cloneRepo.findAll().stream();
    }

    /**
     * Récupère l'artefact ayant l'identifiant unique spécifié.
     *
     * @param id l'identifiant unique de l'artefact à récupérer
     * @return l'artefact ayant l'identifiant unique spécifié, ou null s'il n'existe pas
     */
    public Artefact getFile(Long id) {
        return cloneRepo.findById(id).orElse(null);
    }

    /**
     * Génère une map associant à chaque artefact son identifiant unique et la liste de ses hashes de blocs de code.
     *
     * @return une map associant à chaque artefact son identifiant unique et la liste de ses hashes de blocs de code
     */
    public Map<Long, List<Long>> mapListOfHashByIdArtefact() {
        return cloneRepo.findAll().stream()
                .collect(Collectors.toMap(
                        artefact -> artefact.getId(),
                        artefact -> artefact.getBlocks()
                                .stream().map(p -> p.getHash()).collect(Collectors.toList()))
                );
    }
}


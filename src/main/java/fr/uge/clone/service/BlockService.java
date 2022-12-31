package fr.uge.clone.service;

import fr.uge.clone.backend.ArtefactFile;
import fr.uge.clone.backend.BytecodeBlock;
import fr.uge.clone.backend.Hash;
import fr.uge.clone.model.Artefact;
import fr.uge.clone.model.Block;
import fr.uge.clone.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Classe de service gérant le stockage et la récupération de blocs dans la base de données.
 */
@Service
public class BlockService {
    private final BlockRepository blockRepository;

    /**
     * Construit un nouveau service de gestion des blocs en utilisant le référentiel de blocs donné.
     *
     * @param blockRepository Le référentiel de blocs à utiliser pour le stockage et la récupération de blocs.
     */
    public BlockService(BlockRepository blockRepository){
        this.blockRepository = blockRepository;
    }

    /**
     * Stocke un artefact dans la base de données en créant un bloc pour chaque ligne de l'artefact.
     *
     * @param artefact L'artefact à stocker.
     * @throws Exception Si l'artefact est null ou s'il y a une erreur lors de l'ouverture ou de l'indexation de l'artefact.
     */
    public void storeBlock(Artefact artefact) throws Exception {
        synchronized (this){
            Objects.requireNonNull(artefact);
            var artefactFile = new ArtefactFile("src/main/uploads/" + artefact.getNumVersion());
            artefactFile.open();
            var artefactHash = artefactFile.IndexArtefact();
            for (Hash hash : artefactHash) {
                var block = new Block(hash.getHash(), artefact.getNumVersion(), String.valueOf(hash.getLine()), BytecodeBlock.getBlockLength());
                blockRepository.save(block);
            }
        }
    }

}

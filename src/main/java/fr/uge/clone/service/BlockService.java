package fr.uge.clone.service;

import fr.uge.clone.backend.ArtefactFile;
import fr.uge.clone.backend.BytecodeBlock;
import fr.uge.clone.backend.Hash;
import fr.uge.clone.model.Artefact;
import fr.uge.clone.model.Block;
import fr.uge.clone.repository.ArtefactRepository;
import fr.uge.clone.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class BlockService {
    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository){
        this.blockRepository = blockRepository;
    }

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

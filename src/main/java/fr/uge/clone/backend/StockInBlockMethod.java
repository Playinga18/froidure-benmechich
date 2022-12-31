package fr.uge.clone.backend;

import fr.uge.clone.model.Artefact;
import fr.uge.clone.model.Block;
import fr.uge.clone.repository.BlockRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class StockInBlockMethod {
    private final BlockRepository blockRepository;

    public StockInBlockMethod(BlockRepository blockRepository){
        this.blockRepository = blockRepository;
    }

    public Block storeBlock(Artefact artefact) throws Exception {
        Objects.requireNonNull(artefact);
        var artefactFile = new ArtefactFile(artefact.getNumVersion());
        artefactFile.open();
        var artefactHash = artefactFile.IndexArtefact();
        return null/*blockRepository.save(artefactHash, artefact.getNumVersion(),)*/;
    }

}

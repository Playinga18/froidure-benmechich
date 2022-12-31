package fr.uge.clone.backend;

import fr.uge.clone.model.Artefact;
import fr.uge.clone.model.Block;
import fr.uge.clone.repository.BlockRepository;
import org.springframework.stereotype.Service;
import java.util.Objects;


@Service
public class StockInBlockMethod {
    private final BlockRepository blockRepository;

    public StockInBlockMethod(BlockRepository blockRepository){
        this.blockRepository = blockRepository;
    }

    public void storeBlock(Artefact artefact) throws Exception {
        synchronized (this){
            Objects.requireNonNull(artefact);
            var artefactFile = new ArtefactFile("src/main/uploads");
            artefactFile.open();
            var artefactHash = artefactFile.IndexArtefact();
            for (Hash hash : artefactHash) {
                var block = new Block(hash.getHash(), artefact.getNumVersion(), String.valueOf(hash.getLine()), BytecodeBlock.getBlockLength());
                blockRepository.save(block);
            }
        }
    }

}

package fr.uge.clone.backend;

import fr.uge.clone.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StockInBlockMethod {

    private final BlockRepository blockRepository;

    public StockInBlockMethod(BlockRepository blockRepository){
        this.blockRepository = blockRepository;
    }


}

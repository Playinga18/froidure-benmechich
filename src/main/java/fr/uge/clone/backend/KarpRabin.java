package fr.uge.clone.backend;

import fr.uge.clone.repository.ArtefactRepository;
import fr.uge.clone.service.ArtefactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


public class KarpRabin {

    @Autowired
    private ArtefactService artefactService;

    @Autowired
    private ArtefactRepository cloneRepo;

    private final String[] instructions;
    private Map<Long, List<Long>> data;
    private Hash rHash;

    private HashMap<Long, List<Hash>> map_score;


    public KarpRabin(String[] instructions) {
        this.instructions = instructions;
        this.rHash = new Hash(Arrays.copyOfRange(instructions, 0, BytecodeBlock.getBlockLength()), 0);
        this.data = artefactService.mapListOfHashByIdArtefact();
        this.map_score = new HashMap<Long, List<Hash>>();
    }

    private boolean containsHash(long index){
        if (data.get(index).contains(rHash.getHash())){
            if (map_score.containsKey(index)){
                map_score.get(index).add(rHash);
            }else{
                map_score.put(index, Arrays.asList(rHash));
            }
           return  true;
        }
        return false;
    }

    private int incrementLine(boolean travel){
        if (travel){
            return BytecodeBlock.getBlockLength();
        }
        return 0;
    }

    private int travelHashExist(){
        var travel = false;
        for (var index: data.keySet()){
            if (containsHash(index)){
                travel = true;
            }
        }
        return incrementLine(travel);
    }

    private void travelInstructions(){
        for (var i = 0; i < instructions.length; i++){
            i += travelHashExist();
            rHash.addRolling(Arrays.copyOfRange(instructions, i-BytecodeBlock.getBlockLength(), i), i);
        }
    }

    public ArrayList<Score> KarpRabin(){
        var i = 0;
        var result = new ArrayList<Score>();
        travelInstructions();
        for (var index: map_score.keySet()){
            var score = map_score.get(index).size() / data.get(index).size();
            result.add(new Score(index, score, map_score.get(index)));
        }
        return result;
    }
}

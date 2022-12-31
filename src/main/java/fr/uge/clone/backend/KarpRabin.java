package fr.uge.clone.backend;

import fr.uge.clone.repository.ArtefactRepository;
import fr.uge.clone.service.ArtefactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * Implémente l'algorithme de Karp-Rabin pour trouver des séquences de bytecode similaires dans différents artefacts.
 */
public class KarpRabin {

    @Autowired
    private ArtefactService artefactService;

    @Autowired
    private ArtefactRepository cloneRepo;

    private final String[] instructions;
    private Map<Long, List<Long>> data;
    private Hash rHash;

    private HashMap<Long, List<Hash>> map_score;

    /**
     * Construit un nouvel objet de Karp-Rabin à partir du contenu de l'artefact courant.
     *
     * @param instructions Le contenu de l'artefact courant sous forme de tableau de chaînes de caractères.
     */
    public KarpRabin(String[] instructions) {
        this.instructions = instructions;
        this.rHash = new Hash(Arrays.copyOfRange(instructions, 0, BytecodeBlock.getBlockLength()), 0);
        this.data = artefactService.mapListOfHashByIdArtefact();
        this.map_score = new HashMap<Long, List<Hash>>();
    }

    /**
     * Vérifie si le hash courant se trouve dans la liste de hash de l'artefact d'ID donné.
     * Si oui, ajoute l'index courant à la liste des hash similaires de cet artefact.
     *
     * @param index L'ID de l'artefact à vérifier.
     * @return true si l'index courant se trouve dans la liste d'index de l'artefact d'ID donné, false sinon.
     */
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

    /**
     * Incrémente la position de la ligne courante en fonction de l'état de voyage.
     *
     * @param travel L'état de voyage. Si true, la position de la ligne courante est incrémentée de la longueur d'un block de bytecode.
     * @return La valeur à ajouter à la position de la ligne courante.
     */
    private int incrementLine(boolean travel){
        if (travel){
            return BytecodeBlock.getBlockLength();
        }
        return 0;
    }

    /**
     * Vérifie si au moins un des artefacts contient le hash courant.
     * Si oui, incrémente la position de la ligne courante de la longueur d'un block de bytecode.
     *
     * @return La valeur à ajouter à la position de la ligne courante.
     */
    private int travelHashExist(){
        var travel = false;
        for (var index: data.keySet()){
            if (containsHash(index)){
                travel = true;
            }
        }
        return incrementLine(travel);
    }

    /**
     * Parcours toutes les instructions et vérifie si elles se trouvent dans au moins un des artefacts.
     */
    private void travelInstructions(){
        for (var i = 0; i < instructions.length; i++){
            i += travelHashExist();
            rHash.addRolling(Arrays.copyOfRange(instructions, i-BytecodeBlock.getBlockLength(), i), i);
        }
    }

    /**
     * Effectue un parcours des instructions en utilisant l'algorithme Karp-Rabin pour détecter les similitudes avec les artefacts existants.
     * Pour chaque artefact avec lequel il y a une similitude, un score est calculé et ajouté à la liste des scores.
     *
     * @return La liste des scores obtenus pour chaque artefact avec lequel il y a une similitude.
     */
    public ArrayList<Score> KarpRabin(){
        var result = new ArrayList<Score>();
        travelInstructions();
        for (var index: map_score.keySet()){
            var score = map_score.get(index).size() / data.get(index).size();
            result.add(new Score(index, score, map_score.get(index)));
        }
        return result;
    }
}

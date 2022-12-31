package fr.uge.clone.backend;

import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Représente un artefact Java.
 */
public class ArtefactFile {
    private final String ArtefactName;
    ArrayList<ClassFile> files = new ArrayList<>();

    /**
     * Construit un nouvel artefact avec le nom donné.
     *
     * @param name Le nom de l'artefact.
     * @throws NullPointerException Si le nom est null.
     */
    public ArtefactFile(String name) {
        Objects.requireNonNull(name);
        ArtefactName = name;
    }

    /**
     * Recherche l'artefact avec le nom donné.
     *
     * @return La référence au module trouvé, ou une exception si aucun module n'a été trouvé.
     */
    private ModuleReference finderArtefacts() {
        var finder = ModuleFinder.of(Path.of(ArtefactName));
        return finder.findAll().stream().findFirst().orElseThrow();
    }

    /**
     * Ouvre l'artefact et charge les fichiers de classe qu'il contient.
     *
     * @throws Exception Si une erreur se produit lors de l'ouverture de l'artefact.
     */
    public void open() throws Exception {
        var module = finderArtefacts();
        try(var reader = module.open()) {
            for (var filename : (Iterable<String>) reader.list()::iterator) {
                if (!filename.endsWith(".class")) {
                    continue;
                }
                var tmp = new ClassFile(filename);
                files.add(tmp);
                tmp.open(reader);
            }
        }
    }

    /**
     * Calcule la liste des hashde chaque fichier de classe de l'artefact.
     *
     * @return La liste des hash, sans doublons.
     */
    public ArrayList<Hash> IndexArtefact() {
        var list = new ArrayList<Hash>();
        for (var f: files){
            list.addAll(f.fileToHashList());
        }
        return list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                Hash::getHash,
                                h -> h,
                                (h1, h2) -> h1),
                        m -> new ArrayList<>(m.values())));
    }

    /**
     * Calcule le score de chaque fichier de classe de l'artefact.
     *
     * @return La liste des scores.
     */
    public ArrayList<Score> KPOnArtefact() {
        var list = new ArrayList<Score>();
        for (var f: files){
            list.addAll(f.fileToScoreList());
        }
        return list;
    }

    /**
     * Retourne une représentation de chaîne de caractères de cet artefact et de ses fichiers de classe.
     *
     * @return La représentation de chaîne de caractères de cet artefact et de ses fichiers de classe.
     */
    @Override
    public String toString() {
        var str = new StringBuilder();
        str.append(ArtefactName).append("\n");
        for (var x: files){
            str.append(x.toString());
        }
        return str.toString();
    }

    public static void main(String[] args) throws Exception { }
}

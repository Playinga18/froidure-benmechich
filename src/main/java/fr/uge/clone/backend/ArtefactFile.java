package fr.uge.clone.backend;

import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ArtefactFile {
    private final String ArtefactName;
    ArrayList<ClassFile> files = new ArrayList<>();

    public ArtefactFile(String name){
        Objects.requireNonNull(name);
        ArtefactName = name;
    }

    private ModuleReference finderArtefacts() {
        var finder = ModuleFinder.of(Path.of(ArtefactName));
        return finder.findAll().stream().findFirst().orElseThrow();
    }

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

    public ArrayList<Hash> IndexArtefact(){
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

    public ArrayList<Score> KPOnArtefact(){
        var list = new ArrayList<Score>();
        for (var f: files){
            list.addAll(f.fileToScoreList());
        }
        return list;
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        str.append(ArtefactName).append("\n");
        for (var x: files){
            str.append(x.toString());
        }
        return str.toString();
    }

    private List<ClassFile> getClassFiles() {
        return files;
    }
/*
    public static void main(String[] args) throws Exception {
        var artefact = new ArtefactFile("C:\\Users\\froid\\Downloads");
        artefact.open();
        //System.out.println(artefact.getClassFiles().get(1));
        //System.out.println(artefact.getClassFiles().get(1).fileToHashList());
        System.out.println(artefact.IndexArtefact().size());
        //System.out.println(artefact.KPOnArtefact().size());
    }*/
}

package fr.uge.clone.parser;

import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public class ArtefactFile {
    private String ArtefactName;
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
        var hash = new ArrayList<Hash>();
        for (var f: files){
            hash.addAll(f.fileToHashList());
        }
        return hash;
    }

    public ArrayList<ClassFile> getClassFiles(){ return files; }

    @Override
    public String toString() {
        var str = new StringBuilder();
        str.append(ArtefactName + "\n");
        for (var x: files){
            str.append(x.toString());
        }
        return str.toString();
    }

    public static void main(String[] args) throws Exception {
        var artefact = new ArtefactFile("C:\\Users\\froid\\Downloads");
        artefact.open();
        System.out.println(artefact.getClassFiles().get(1));
    }
}

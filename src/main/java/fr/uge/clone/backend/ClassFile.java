package fr.uge.clone.backend;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.module.ModuleReader;
import java.util.ArrayList;

/**
 * Représente un fichier de classe Java.
 */
public class ClassFile {
    private final String filename;
    private BytecodeBlock code;

    /**
     * Construit un nouveau fichier de classe avec le nom de fichier donné.
     *
     * @param filename Le nom du fichier.
     */
    public ClassFile(String filename){
        this.code = new BytecodeBlock(0, "");
        this.filename = filename;
    }

    /**
     * Ouvre le fichier de classe et charge son contenu.
     *
     * @param reader Le lecteur de modules utilisé pour lire le fichier de classe.
     * @throws Exception Si une erreur se produit lors de l'ouverture ou du chargement du fichier.
     */
    public void open(ModuleReader reader) throws Exception {
        var writer = new StringWriter();
        try(var inputStream = reader.open(filename).orElseThrow()) {
            var classReader = new ClassReader(inputStream);
            TraceClassVisitor traceVisitor = new TraceClassVisitor(new PrintWriter(writer));
            classReader.accept(traceVisitor, 0);
        }catch (IOException t) {
            throw new Exception(t);
        }
        var tmp = new Parser().parsingClass(writer.toString());
        code = new BytecodeBlock(0, tmp);
    }

    /**
     * Calcule le score de chaque ligne du fichier de classe.
     *
     * @return La liste des scores.
     */
    public ArrayList<Score> fileToScoreList(){
        return code.KP();
    }

    /**
     * Calcule la liste des hash du bloc du fichier.
     *
     * @return La liste des index.
     */
    public ArrayList<Hash> fileToHashList() {
        return code.BlockToHash();
    }

    /**
     * Retourne une représentation de chaîne de caractères de ce fichier de classe.
     *
     * @return La représentation de chaîne de caractères de ce fichier de classe.
     */
    @Override
    public String toString() {
        return code.toString();
    }
}


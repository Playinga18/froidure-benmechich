package fr.uge.clone.backend;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.module.ModuleReader;
import java.util.ArrayList;

public class ClassFile {

    private final String filename;
    private BytecodeBlock code;

    public ClassFile(String filename){
        this.code = new BytecodeBlock(0, "");
        this.filename = filename;
    }

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

    public ArrayList<Score> fileToScoreList(){ return code.KarpRabin(); }

    public ArrayList<Hash> fileToHashList() {
        return code.BlockToHash();
    }

    @Override
    public String toString() {
        return code.toString();
    }
}

package fr.uge.clone.parser;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;
import java.util.Objects;

public class ClassFile {
    private final String filename;
    private BytecodeBlock code;

    public ClassFile(String filename){
        Objects.requireNonNull(filename);
        this.filename = filename;
        this.code = new BytecodeBlock(0, "");
    }

    public void open() throws Exception {
        var writer = new StringWriter();
        try {
            ClassReader reader = new ClassReader(new FileInputStream(filename));
            TraceClassVisitor traceVisitor = new TraceClassVisitor(new PrintWriter(writer));
            reader.accept(traceVisitor, 0);
        }catch (IOException t) {
            throw new Exception(t);
        }
        var tmp = new Parser().parsingClass(writer.toString());
        code = new BytecodeBlock(0, tmp);
    }

    public boolean codeIsEmpty() {
        return code.getBlock().length == 0;
    }

    public BytecodeBlock getCode() {
        return this.code;
    }

    public static void main(String[] args) throws Exception {
        var file = new ClassFile("C:\\test\\Toy.class");
        file.open();
        System.out.println(file.getCode());
        System.out.println(file.getCode().BlockToHash());
    }
}

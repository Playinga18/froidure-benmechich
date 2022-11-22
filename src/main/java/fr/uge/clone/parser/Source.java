package fr.uge.clone.parser;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.io.Writer;

public class Source extends ClassVisitor {
    public Source(Writer writer) {
        super(Opcodes.ASM9, new TraceClassVisitor(new PrintWriter(writer)));
    }
}

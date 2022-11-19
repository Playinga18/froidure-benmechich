package fr.uge.clone.parser;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static void main(String[] args) throws IOException {
        var finder = ModuleFinder.of(Path.of("test3.jar"));
        var moduleReference = finder.findAll().stream().findFirst().orElseThrow();
        try(var reader = moduleReference.open()) {
            for(var filename: (Iterable<String>) reader.list()::iterator) {
                if (!filename.endsWith(".class")) {
                    continue;
                }
                try(var inputStream = reader.open(filename).orElseThrow()) {
                    var classReader = new ClassReader(inputStream);
                    classReader.accept(new ClassVisitor(Opcodes.ASM9) {

                        private List<Long> listHash = new ArrayList<Long>();

                        @Override
                        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                            var method = new MethodVisitor(Opcodes.ASM9) {
                                List<Integer> listInst = new ArrayList<Integer>();

                                public List<Integer> getList(){
                                    return listInst;
                                }
                                @Override
                                public void visitInsn(int opcode) {
                                    listInst.add(opcode);
                                }

                                @Override
                                public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                                    listInst.add(opcode);
                                }

                                @Override
                                public void visitEnd(){
                                    //System.err.println(listInst.toString());
                                    var hash = 0L; //hashing future
                                    hash = listInst.stream().mapToInt(Integer::intValue).sum();
                                    listHash.add(hash);
                                }
                            };
                            return method;
                        }
                    }, 0);
                }
            }
        }
    }
}

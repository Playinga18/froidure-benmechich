package fr.uge.clone.parser;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;

public class Parser {
    public static void main(String[] args) throws IOException {
        var finder = ModuleFinder.of(Path.of("C:\\\\Users\\\\froid\\\\Downloads"));
        var moduleReference = finder.findAll().stream().findFirst().orElseThrow();
        try(var reader = moduleReference.open()) {
            for(var filename: (Iterable<String>) reader.list()::iterator) {
                if (!filename.endsWith(".class")) {
                    continue;
                }
                try(var inputStream = reader.open(filename).orElseThrow()) {
                    var classReader = new ClassReader(inputStream);
                    var writer = new StringWriter();
                    classReader.accept(new Source(writer) {
                    }, 0);
                }
                return;
            }
        }
    }
}

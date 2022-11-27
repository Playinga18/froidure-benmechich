package fr.uge.clone.parser;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Parser {

    private ModuleReference finderArtefacts(String name) {
        var finder = ModuleFinder.of(Path.of(name));
        return finder.findAll().stream().findFirst().orElseThrow();
    }

    private void ParseArtefacts(ModuleReference module) throws IOException {
        try(var reader = module.open()) {
            for (var filename : (Iterable<String>) reader.list()::iterator) {
                if (!filename.endsWith(".class")) {
                    continue;
                }
                var writer = new StringWriter();
                try (var inputStream = reader.open(filename).orElseThrow()) {
                    var classReader = new ClassReader(inputStream);
                    TraceClassVisitor traceVisitor = new TraceClassVisitor(new PrintWriter(writer));
                    classReader.accept(traceVisitor, 0);
                }
                var instClass = parsingClass(writer.toString());
            }
        }
    }

    private String parseUselessStuff(String str){
        var pat = Pattern.compile("(//.*)").matcher(str).replaceAll("");
        //pat = Pattern.compile("\\t{2,}").matcher(pat).replaceAll("");
        //pat = Pattern.compile("(L\\d).*").matcher(pat).replaceAll("");
        pat = Pattern.compile("\\(.*\\)[A-Z]").matcher(pat).replaceAll("");
        pat = Pattern.compile("\\S*\\n{2,}").matcher(pat).replaceAll("");
        return  pat;
    }

    public String parsingClass(String inst){
        var pat = parseUselessStuff(inst);

        return pat;
    }
}

package fr.uge.clone.parser;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.FileInputStream;
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

    private void ParseArtefacts(ModuleReference module){
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
        }catch ( IOException e){
            throw new RuntimeException(e);
        }
    }

    private void Opentest() throws Exception {
        var writer = new StringWriter();
        try {
            ClassReader reader = new ClassReader(new FileInputStream("C:\\test\\Toy.class"));
            TraceClassVisitor traceVisitor = new TraceClassVisitor(new PrintWriter(writer));
            reader.accept(traceVisitor, 0);
        } catch (IOException t) {
            throw new Exception(t);
        }
        var instClass = parsingClass(writer.toString());
        //System.out.println(writer);
    }

    private String parseUselessStuff(String str){
        var pat = Pattern.compile("(//.*)").matcher(str).replaceAll("");
        //pat = Pattern.compile("(L\\d).*").matcher(pat).replaceAll("");
        //pat = Pattern.compile("\\(.*\\)[A-Z]").matcher(pat).replaceAll("");
        pat = Pattern.compile("(\\S*\\n){2}").matcher(pat).replaceAll("");
        return  pat;
    }

    private String parsingClass(String inst){
        var pat = parseUselessStuff(inst);

        System.out.println(pat);
        //System.out.println(inst);
        return inst;
    }

    public void parse(String name) throws Exception {
        //Opentest();
        ParseArtefacts(finderArtefacts(name));
    }

    public static void main(String[] args) throws Exception {
        var parser = new Parser();
        parser.parse("C:\\\\Users\\\\froid\\\\Downloads");
    }
}

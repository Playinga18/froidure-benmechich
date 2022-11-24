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

    public static ModuleReference finderArtefacts(String name) {
        var finder = ModuleFinder.of(Path.of(name));
        return finder.findAll().stream().findFirst().orElseThrow();
    }

    public static void OpenArtefacts(ModuleReference module){
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
                parsing(writer.toString());
            }
        }catch ( IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void Opentest() throws Exception {
        var writer = new StringWriter();
        try {
            ClassReader reader = new ClassReader(new FileInputStream("C:\\test\\Hello.class"));
            TraceClassVisitor traceVisitor = new TraceClassVisitor(new PrintWriter(writer));
            reader.accept(traceVisitor, 0);
        } catch (IOException t) {
            throw new Exception(t);
        }
        parsing(writer.toString());
        //System.out.println(writer);
    }

    private static String parseUseless(String str){
        var pat = Pattern.compile("(//.*)").matcher(str).replaceAll("");
        pat = Pattern.compile("(L[0-9])").matcher(pat).replaceAll("");
        pat = Pattern.compile("(?:\\h*\\n){2}").matcher(pat).replaceAll("");
        return  pat;
    }

    public static String parsing(String inst){
        var pat = parseUseless(inst);

        System.out.println(pat);
        //System.out.println(inst);
        return inst;
    }

    public static void main(String[] args) throws Exception {
        Opentest();
        //OpenArtefacts(finderArtefacts("C:\\\\Users\\\\froid\\\\Downloads"));
    }
}

package fr.uge.clone.parser;

import java.util.regex.Pattern;

public class Parser {

    private String parseUselessStuff(String str){
        var pat = Pattern.compile("(//.*)").matcher(str).replaceAll("");
        pat = Pattern.compile("^\\s*").matcher(pat).replaceAll("");
        pat = Pattern.compile("\\s*LINENUMBER.*").matcher(pat).replaceAll("");
        pat = Pattern.compile("\\s+L\\d").matcher(pat).replaceAll("");
        pat = Pattern.compile("\\s*\\(.*\\)[A-Z]").matcher(pat).replaceAll("");
        pat = Pattern.compile("\\s*\\n{2,}").matcher(pat).replaceAll("");
        return  pat;
    }

    public String parsingClass(String inst){
        var pat = parseUselessStuff(inst);

        return pat;
    }
}

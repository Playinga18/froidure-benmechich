package fr.uge.clone.backend;

import java.util.regex.Pattern;

public class Parser {

    private String parseUselessStuff(String str){

        return Pattern
                .compile(
                        "private|public|protected|final|abstract|" +
                                "//.*|" +
                                "L\\d|" +
                                "LINENUMBER.*|" +
                                "\\(.*\\)[A-Z]|" +
                                "[;}{]|" +
                                "[A-Z]?([a-z]+)/|" +
                                "([A-Z][a-z]+)+[./]")
                .matcher(str)
                .replaceAll("");
    }

    public String parsingClass(String inst){
        StringBuilder res = new StringBuilder();
        for (var str: parseUselessStuff(inst).split("\n")){
            var tmp = str.trim();
            if (!tmp.equals("")) {
                res.append(tmp.concat("\n"));
            }
        }
        return res.toString();
    }
}

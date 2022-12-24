package fr.uge.clone.parser;

import java.util.regex.Pattern;

public class Parser {

    private String parseUselessStuff(String str){

        return Pattern
                .compile(
                        "private|public|//.*|L\\d|LINENUMBER.*|\\(.*\\)[A-Z]|[;}{]|[A-Z]?([a-z]+)/")
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

package fr.uge.clone.backend;

import java.util.regex.Pattern;

/**
 * Classe permettant de parser du bytecode.
 * Le bytecode est une version simplifiée du code source d'une classe en Java.
 * Il permet de retrouver la structure originale d'une classe en Java, mais en
 * enlevant tous les éléments inutiles tels que les commentaires, les
 * déclarations de variables ou encore les noms de packages.
 */
public class Parser {

    /**
     * Méthode permettant de supprimer les éléments inutiles du bytecode.
     *
     * @param str le bytecode à parser
     * @return le bytecode nettoyé
     */
    private String parseUselessStuff(String str) {
        // Insérer ici la description de l'expression régulière utilisée pour nettoyer le bytecode
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

    /**
     * Méthode permettant de nettoyer et de formatter le bytecode.
     *
     * @param inst le bytecode à nettoyer et formatter
     * @return le bytecode nettoyé et formaté
     */
    public String parsingClass(String inst) {
        StringBuilder res = new StringBuilder();
        for (var str : parseUselessStuff(inst).split("\n")) {
            var tmp = str.trim();
            if (!tmp.equals("")) {
                res.append(tmp.concat("\n"));
            }
        }
        return res.toString();
    }
}

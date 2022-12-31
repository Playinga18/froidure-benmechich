import fr.uge.clone.backend.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void testParseUselessStuff() {
        assertEquals("", parser.parsingClass(""));
        assertEquals("", parser.parsingClass("private"));
        assertEquals("", parser.parsingClass("public"));
        assertEquals("", parser.parsingClass("protected"));
        assertEquals("", parser.parsingClass("final"));
        assertEquals("", parser.parsingClass("abstract"));
        assertEquals("", parser.parsingClass("// commentaire"));
        assertEquals("", parser.parsingClass("LINENUMBER 123"));
        assertEquals("", parser.parsingClass("(args)Z"));
        assertEquals("", parser.parsingClass(";"));
        assertEquals("", parser.parsingClass("{"));
        assertEquals("", parser.parsingClass("}"));
        assertEquals("", parser.parsingClass("java/lang/"));
        assertEquals("", parser.parsingClass("JavaLang/"));
        assertEquals("", parser.parsingClass("JavaLang."));
        assertEquals("", parser.parsingClass("JavaLang/String."));
    }

    @Test
    public void testParsingClass() {
        assertEquals("", parser.parsingClass(""));
        assertEquals("", parser.parsingClass("\n"));
        assertEquals("", parser.parsingClass(" \n"));
        assertEquals("", parser.parsingClass("\n\n"));
        assertEquals("", parser.parsingClass(" \n\n "));
        assertEquals("test\n", parser.parsingClass("test"));
        assertEquals("test\n", parser.parsingClass("test\n"));
        assertEquals("test\n", parser.parsingClass("test\n\n"));
        assertEquals("test\n", parser.parsingClass(" \ntest"));
    }
}

import fr.uge.clone.backend.BytecodeBlock;
import fr.uge.clone.backend.Hash;
import fr.uge.clone.backend.Score;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class bytecodeTest {
    @Test
    void testConstructor_withValidInput_shouldCreateBytecodeBlock() {
        String str = """
                class AnnotationVisitor
                I api
                AnnotationVisitor av
                <init>
                ALOAD 0
                ILOAD 1
                ACONST_NULL
                INVOKESPECIAL <init>
                RETURN
                LOCALVARIABLE this AnnotationVisitor   0
                LOCALVARIABLE api I   1
                """;
        BytecodeBlock block = new BytecodeBlock(0, str);
        assertNotNull(block);
    }

    @Test
    void testConstructor_withNegativeLine_shouldThrowIllegalArgumentException() {
        String str = """
                class AnnotationVisitor
                I api
                AnnotationVisitor av
                <init>
                ALOAD 0
                ILOAD 1
                ACONST_NULL
                INVOKESPECIAL <init>
                RETURN
                LOCALVARIABLE this AnnotationVisitor   0
                LOCALVARIABLE api I   1
                """;
        assertThrows(IllegalArgumentException.class, () -> new BytecodeBlock(-1, str));
    }

    @Test
    void testConstructor_withNullBlock_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BytecodeBlock(0, null));
    }

    @Test
    void testBlockToHash_withValidBlock_shouldReturnArrayListOfHashes() {
        String str = """
                class AnnotationVisitor
                I api
                AnnotationVisitor av
                <init>
                ALOAD 0
                ILOAD 1
                ACONST_NULL
                INVOKESPECIAL <init>
                RETURN
                LOCALVARIABLE this AnnotationVisitor   0
                LOCALVARIABLE api I   1
                """;
        BytecodeBlock block = new BytecodeBlock(0, str);
        ArrayList<Hash> hashes = block.BlockToHash();
        assertNotNull(hashes);
        assertFalse(hashes.isEmpty());
    }
}

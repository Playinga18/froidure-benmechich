import fr.uge.clone.backend.Hash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashTest {
    @Test
    public void testHash() {
        assertThrows(NullPointerException.class, () -> new Hash(null, 0));
        assertThrows(IllegalArgumentException.class, () -> new Hash(new String[0], -1));
        var hash = new Hash(new String[0], 0);
        assertEquals(0, hash.getLine());
        assertEquals(0, hash.getHash());
        hash = new Hash(new String[]{"test1", "test2"}, 0);
        assertEquals(0, hash.getLine());
        assertEquals(1421, hash.getHash());
        hash = new Hash(new String[]{"test1", "test2","test3"}, 1);
        assertEquals(1, hash.getLine());
        assertEquals(1932, hash.getHash());
    }

    @Test
    public void testAddRolling() {
        var hash = new Hash(new String[0], 0);
        assertEquals(0, hash.getLine());
        assertEquals(0, hash.getHash());
        hash = hash.addRolling(new String[0], 1);
        assertEquals(1, hash.getLine());
        assertEquals(0, hash.getHash());
        hash = hash.addRolling(new String[]{"test"}, 1);
        assertEquals(2, hash.getLine());
        assertEquals(49, hash.getHash());
        hash = hash.addRolling(new String[]{"test1", "test2"}, 1);
        assertEquals(3, hash.getLine());
        assertEquals(1421, hash.getHash());
    }

}

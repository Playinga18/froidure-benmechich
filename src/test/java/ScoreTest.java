import fr.uge.clone.backend.Hash;
import fr.uge.clone.backend.Score;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    @Test
    void testConstructor() {
        List<Hash> hash = new ArrayList<>();
        Score score = new Score(1, 2, 0.5, hash);
        assertEquals(1, score.idA1());
        assertEquals(2, score.idA2());
        assertEquals(0.5, score.score());
        assertEquals(hash, score.hash());
    }
}

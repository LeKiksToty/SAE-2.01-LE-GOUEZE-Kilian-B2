package org.uphf.sae01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MineTest {

    @Test
    public void testExtraire() {
        Mine m = new Mine(1, "OR", 50, 0, 0);
        int extrait = m.extraire(10);
        assertEquals(10, extrait);

        Mine m2 = new Mine(2, "NI", 5, 0, 0);
        int extraitLimite = m2.extraire(10);
        assertEquals(5, extraitLimite);
        System.out.println("[MineTest] Extraction : OK");
    }
}
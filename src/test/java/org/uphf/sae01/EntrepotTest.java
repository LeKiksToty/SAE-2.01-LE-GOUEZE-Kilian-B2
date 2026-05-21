package org.uphf.sae01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntrepotTest {

    @Test
    public void testDepot() {
        Robot r = new Robot(1, "OR", 10, 5, 0, 0);
        Mine m = new Mine(1, "OR", 20, 0, 1);
        r.recolter(m);

        Entrepot e = new Entrepot(1, "OR", 0, 2);
        r.deposer(e);

        assertEquals(0, r.getStockActuel());
        assertEquals(5, e.getStockActuel());
        System.out.println("[EntrepotTest] Depot : OK");
    }
}
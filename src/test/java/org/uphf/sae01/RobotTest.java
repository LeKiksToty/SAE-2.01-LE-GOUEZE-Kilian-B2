package org.uphf.sae01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {

    @Test
    public void testDeplacements() {
        Robot r = new Robot(1, "OR", 10, 5, 5, 5);
        r.avancer("N");
        assertEquals(4, r.getLigne());
        System.out.println("[RobotTest] Deplacement : OK");
    }

    @Test
    public void testRecolteEtCapacite() {
        Robot r = new Robot(1, "OR", 10, 5, 0, 0);
        Mine m = new Mine(1, "OR", 20, 0, 0);
        r.recolter(m);
        assertEquals(5, r.getStockActuel());
        System.out.println("[RobotTest] Recolte : OK");
    }
}
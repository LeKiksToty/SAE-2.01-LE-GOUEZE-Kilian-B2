package org.uphf.sae01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MondeTest {

    @Test
    public void testCollisionEau() {
        Monde monde = new Monde();
        monde.getSecteur(4, 5).setEstEau(true);
        assertFalse(monde.getSecteur(4, 5).estDisponible());
        System.out.println("[MondeTest] Eau : OK");
    }

    @Test
    public void testSecteurLibre() {
        Monde monde = new Monde();
        monde.getSecteur(2, 2).setEstEau(false);
        assertTrue(monde.getSecteur(2, 2).estDisponible());
        System.out.println("[MondeTest] Libre : OK");
    }
}
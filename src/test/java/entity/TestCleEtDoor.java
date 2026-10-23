package entity;

import simple.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCleEtDoor {
    
    @Test
    void testCle() {
        Position pos = new Position(3, 3);
        Cle cle = new Cle(pos);
        
        assertEquals(pos, cle.getPos());
        assertFalse(cle.estRamassee());
        
        cle.ramasser();
        assertTrue(cle.estRamassee());
    }
    
    @Test
    void testDoor() {
        Position pos = new Position(7, 7);
        Door door = new Door(pos);
        
        assertEquals(pos, door.getPos());
        assertFalse(door.estOuverte());
        
        door.ouvrir();
        assertTrue(door.estOuverte());
    }
}
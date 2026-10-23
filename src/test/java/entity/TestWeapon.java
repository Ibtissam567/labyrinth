package entity;

import simple.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestWeapon {
    
    @Test
    void testWeaponCreation() {
        Position pos = new Position(4, 4);
        Weapon epee = new Weapon(pos, WeaponType.EPEE);
        Weapon arc = new Weapon(pos, WeaponType.ARC);
        
        assertEquals(pos, epee.getPos());
        assertEquals(WeaponType.EPEE, epee.getType());
        assertFalse(epee.estRamassee());
        
        assertEquals(WeaponType.ARC, arc.getType());
    }
    
    @Test
    void testRamasserWeapon() {
        Weapon weapon = new Weapon(new Position(2, 2), WeaponType.EPEE);
        
        weapon.ramasser();
        assertTrue(weapon.estRamassee());
    }
}
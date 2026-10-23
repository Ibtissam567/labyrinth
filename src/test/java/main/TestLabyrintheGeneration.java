package main;

import simple.Level;
import simple.Position;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class TestLabyrintheGeneration {
    
    @Test
    void testLabyrintheCreation() {
        Level level = Level.LEVEL_1;
        Labyrinthe laby = new Labyrinthe(level);
        char[][] grille = laby.getGrille();
        
        assertNotNull(grille);
        assertEquals(level.height, grille.length);
        assertEquals(level.width, grille[0].length);
    }
    
    @Test
    void testPlacerAccessible() {
        Level level = Level.LEVEL_1;
        Labyrinthe laby = new Labyrinthe(level);
        char[][] grille = laby.getGrille();
        
        Position centre = new Position(level.height / 2, level.width / 2);
        Set<Position> accessibles = laby.trouverZonesAccessibles(centre);
        Set<Position> occupees = new java.util.HashSet<>();
        occupees.add(centre);
        
        Position pos = laby.placerAccessible(occupees, accessibles);
        
        assertNotNull(pos);
        assertTrue(pos.x > 0 && pos.x < level.height - 1);
        assertTrue(pos.y > 0 && pos.y < level.width - 1);
        assertEquals(' ', grille[pos.x][pos.y]);
    }
    
    @Test
    void testTrouverZonesAccessibles() {
        Level level = Level.LEVEL_1;
        Labyrinthe laby = new Labyrinthe(level);
        
        Position centre = new Position(level.height / 2, level.width / 2);
        Set<Position> accessibles = laby.trouverZonesAccessibles(centre);
        
        assertNotNull(accessibles);
        assertFalse(accessibles.isEmpty());
        assertTrue(accessibles.contains(centre));
    }
    
    @Test
    void testPlacerLoinDe() {
        Level level = Level.LEVEL_1;
        Labyrinthe laby = new Labyrinthe(level);
        
        Position ref = new Position(level.height / 2, level.width / 2);
        Position loin = laby.placerLoinDe(ref);
        
        assertNotNull(loin);
        int distance = Math.abs(loin.x - ref.x) + Math.abs(loin.y - ref.y);
        assertTrue(distance > 0);
    }
    
    // ============ TESTS DE DÉTECTION DE BUGS ============
    
    @Test
    void testBugAccessibiliteComplete() {
        System.out.println("=== DÉTECTION BUG: Accessibilité complète ===");
        
        Level level = Level.LEVEL_1;
        Labyrinthe laby = new Labyrinthe(level);
        char[][] grille = laby.getGrille();
        
        Position centre = new Position(level.height / 2, level.width / 2);
        Set<Position> accessibles = laby.trouverZonesAccessibles(centre);
        
        // Vérifier qu'il y a un chemin depuis le centre
        assertTrue(accessibles.size() > 10, 
            "BUG: Pas assez de cases accessibles depuis le centre");
        
        // Vérifier que le centre n'est pas un mur
        assertEquals(' ', grille[centre.x][centre.y],
            "BUG: Le centre de départ devrait être une case vide");
    }
    
    @Test
    void testBugPasDeCaseLibre() {
        System.out.println("=== DÉTECTION BUG: Pas de case libre ===");
        
        Level level = Level.LEVEL_1;
        Labyrinthe laby = new Labyrinthe(level);
        
        // Créer un set d'occupation qui contient toutes les cases
        Set<Position> occupees = new java.util.HashSet<>();
        for (int i = 0; i < level.height; i++) {
            for (int j = 0; j < level.width; j++) {
                occupees.add(new Position(i, j));
            }
        }
        
        Set<Position> accessibles = new java.util.HashSet<>();
        Position pos = laby.placerAccessible(occupees, accessibles);
        
        // Même sans cases libres, ne devrait pas planter
        assertNotNull(pos, 
            "BUG: placerAccessible ne devrait pas retourner null même sans cases libres");
    }
    
    @Test
    void testBugGrilleValide() {
        System.out.println("=== DÉTECTION BUG: Grille valide ===");
        
        Level level = Level.LEVEL_1;
        Labyrinthe laby = new Labyrinthe(level);
        char[][] grille = laby.getGrille();
        
        // Vérifier les bords sont des murs
        for (int i = 0; i < level.height; i++) {
            assertEquals('#', grille[i][0], 
                "BUG: Bord gauche devrait être un mur");
            assertEquals('#', grille[i][level.width - 1],
                "BUG: Bord droit devrait être un mur");
        }
        
        for (int j = 0; j < level.width; j++) {
            assertEquals('#', grille[0][j],
                "BUG: Bord supérieur devrait être un mur");
            assertEquals('#', grille[level.height - 1][j],
                "BUG: Bord inférieur devrait être un mur");
        }
    }
}
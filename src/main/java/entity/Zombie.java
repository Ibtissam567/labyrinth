package entity;

import simple.Position;

public class Zombie {
    private Position pos;

    public Zombie(Position pos) { this.pos = pos; }
    public Position getPos() { return pos; }

    public void moveTowards(Position hero, char[][] grille) {
        if (hero == null || grille == null || grille.length == 0 || grille[0].length == 0) {
            return;
        }
        if (distance(hero) > 0 && distance(hero) < 3) {
            int dx = Integer.compare(hero.x, pos.x);
            int dy = Integer.compare(hero.y, pos.y);
            int newX = pos.x + dx;
            int newY = pos.y + dy;

            // Le zombie s'arrête sur la case voisine : il ne se superpose pas au héros.
            if (newX == hero.x && newY == hero.y) {
                return;
            }

            if (newX >= 0 && newX < grille.length && newY >= 0 && newY < grille[0].length) {
                if (grille[newX][newY] != '#') {
                    pos.x = newX;
                    pos.y = newY;
                }
            }
        }
    }

    private int distance(Position p) {
        return Math.abs(p.x - pos.x) + Math.abs(p.y - pos.y);
    }
}
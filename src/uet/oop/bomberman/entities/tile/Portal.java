package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {
    protected Board _board;

    public Portal(int x, int y, Board board, Sprite sprite) {
        super(x, y, sprite);
        _board = board;
    }

    @Override
    public boolean collide(Entity e) {
        // Xử lý khi Bomber đi vào
        if (e instanceof Bomber) {
            // Nếu còn enemy thì không cho đi vào
            if (_board.detectNoEnemies() == false)
                return false;

            // Nếu bomber đứng trên portal thì chuyển sang level tiếp theo
            if (e.getXTile() == getX() && e.getYTile() == getY()) {
                if (_board.detectNoEnemies()) {
                    _board.nextLevel();
                    //Sound.play("CRYST_UP");
                }
            }

            return true;
        }

        return true;
    }
}
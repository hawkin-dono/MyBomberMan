package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    public Kondoria(int x, int y, Board board) {
        super(x, y, board, Sprite.kondoria_dead, 0.5, 100);

        _sprite = Sprite.kondoria_left1;

        _ai = new AILow();
        _direction = _ai.calculateDirection();

    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, _animate, 60);
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, _animate, 60);
                break;
        }
    }
}

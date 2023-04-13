package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {

	public Balloon(int x, int y, Board board) {
		super(x, y, board, Sprite.balloon_dead, 0.5, 100);
		
		_sprite = Sprite.balloon_left1;
		
		_ai = new AILow();
		_direction = _ai.calculateDirection();
                
	}

	@Override
	protected void chooseSprite() {
		switch(_direction) {
			case 0:
			case 1:
					_sprite = Sprite.movingSprite(Sprite.balloon_right1, Sprite.balloon_right2, Sprite.balloon_right3, _animate, 60);
				break;
			case 2:
			case 3:
					_sprite = Sprite.movingSprite(Sprite.balloon_left1, Sprite.balloon_left2, Sprite.balloon_left3, _animate, 60);
				break;
		}
	}
}

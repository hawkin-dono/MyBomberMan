package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound;

public class Portal extends Tile {
	protected Board _board;
	public Portal(int x, int y,Board board, Sprite sprite) {
		super(x, y, sprite);
		_board = board;
	}
	
	@Override
	public boolean collide(Entity e) {//xu li khi 2 entity va cham
                                          //true cho di qua
                                          //false khong cho di qua
		// TODO: xử lý khi Bomber đi vào
		if(e instanceof Bomber ) {
			
			if(_board.detectNoEnemies() == false)
				return false;
			
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_board.detectNoEnemies()){
					_board.nextLevel();
					Sound.playSound("res/sound/CRYST_UP.wav",5500);
				}
			}
			
			return true;
		}
		
		return true;
	}

}

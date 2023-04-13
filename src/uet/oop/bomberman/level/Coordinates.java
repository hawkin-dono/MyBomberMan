package uet.oop.bomberman.level;

import uet.oop.bomberman.Game;

public class Coordinates {
	
	// Chuyển đổi giá trị pixel thành giá trị tile tương ứng
	public static int pixelToTile(double i) {
		return (int)(i / Game.TILES_SIZE);
	}
	
	// Chuyển đổi giá trị index của tile thành giá trị pixel tương ứng
	public static int tileToPixel(int i) {
		return i * Game.TILES_SIZE;
	}
	
	// Chuyển đổi giá trị của tile thành giá trị pixel tương ứng
	public static int tileToPixel(double i) {
		return (int)(i * Game.TILES_SIZE);
	}
}


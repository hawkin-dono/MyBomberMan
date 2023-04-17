package uet.oop.bomberman;

import uet.oop.bomberman.gui.Frame;

public class BombermanGame {
	public static void main(String[] args) {
		Sound.playSound("res/sound/soundtrack.wav",1000);
		new Frame();
	}
}

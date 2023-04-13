package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {

	private String _path = "res/textures/classic.png";
	public final int SIZE;
	public int[] _pixels;
	
	public static SpriteSheet tiles = new SpriteSheet(256);
	
	public SpriteSheet(int size) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
//			URL a = SpriteSheet.class.getResource(_path);
//			BufferedImage image = ImageIO.read(a);
			BufferedImage image = ImageIO.read(new File(_path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, _pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}

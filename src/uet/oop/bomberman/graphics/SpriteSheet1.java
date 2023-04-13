package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet1 {

	private String _path;
	public final int SIZE;
	public int[] _pixels;
	
	public static SpriteSheet1 tiles = new SpriteSheet1("res/textures/tile.png", 256);
	
	public SpriteSheet1(String path, int size) {
		_path = path;
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
//			URL a = SpriteSheet1.class.getResource(_path);
//			//assert a != null;
//			BufferedImage image = ImageIO.read(a);
			BufferedImage image = ImageIO.read(new File(_path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, _pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			//System.exit(0);
		}
	}
}

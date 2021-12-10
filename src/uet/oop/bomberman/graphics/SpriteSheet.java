package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {

	private String _path;
	public final int SIZE;
	public int[] _pixels;
	public BufferedImage image;

	public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);
	public static SpriteSheet tiles2 = new SpriteSheet("/textures/Up.png", 128);
	public static SpriteSheet tiles3 = new SpriteSheet("/textures/Down.png", 128);
	public static SpriteSheet tiles4 = new SpriteSheet("/textures/Left.png", 128);
	public static SpriteSheet tiles5 = new SpriteSheet("/textures/Right.png", 128);
	public static SpriteSheet tiles6 = new SpriteSheet("/textures/Up_2.png", 128);
	public static SpriteSheet tiles7 = new SpriteSheet("/textures/Down_2.png", 128);
	public static SpriteSheet tiles8 = new SpriteSheet("/textures/Left_2.png", 128);
	public static SpriteSheet tiles9 = new SpriteSheet("/textures/Right_2.png", 128);
	public static SpriteSheet tiles10 = new SpriteSheet("/textures/up_1.png", 128);
	public static SpriteSheet tiles11 = new SpriteSheet("/textures/down_1.png", 128);
	public static SpriteSheet tiles12 = new SpriteSheet("/textures/left_1.png", 128);
	public static SpriteSheet tiles13 = new SpriteSheet("/textures/right_1.png", 128);
	public static SpriteSheet Bgr = new SpriteSheet("/textures/0.png", 16);
	public static SpriteSheet Bgr1 = new SpriteSheet("/textures/1.png", 16);
	public static SpriteSheet Bgr2 = new SpriteSheet("/textures/2.png", 16);
	public static SpriteSheet Bgr3 = new SpriteSheet("/textures/3.png", 16);
	public static SpriteSheet Bgr4 = new SpriteSheet("/textures/4.png", 16);
	public static SpriteSheet Bgr5 = new SpriteSheet("/textures/5.png", 16);
	public static SpriteSheet Bgr6 = new SpriteSheet("/textures/6.png", 16);
	public static SpriteSheet Bgr7 = new SpriteSheet("/textures/7.png", 16);

	public SpriteSheet(String path, int size) {
		_path = path;
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			URL a = SpriteSheet.class.getResource(_path);
			image = ImageIO.read(a);
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, _pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}

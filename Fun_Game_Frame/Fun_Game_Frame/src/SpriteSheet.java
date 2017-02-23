import java.awt.image.BufferedImage;


public class SpriteSheet{
	
	private final BufferedImage image;
        private final int WIDTH = 64;
        private final int HEIGHT = 128;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row){
		BufferedImage img = image.getSubimage((col-1) * WIDTH, (row-1)*HEIGHT, WIDTH, HEIGHT);
		return img;
	}
        	
        
}

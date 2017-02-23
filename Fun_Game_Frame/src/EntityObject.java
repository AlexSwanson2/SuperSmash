import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public abstract class EntityObject{
	
	protected double x, y, VelX, VelY, width, height;
        protected ID id;
        SpriteSheet sprite;
        LinkedList<Sound> sound;
	

	public EntityObject(double x, double y, double width, double height, double VelX, double VelY, ID id, BufferedImage spriteSheet) {
                this.id = id;
		this.x = x;
		this.y = y;
                this.width = width;
                this.height = height;
		this.VelX = VelX;
		this.VelY = VelY;
                sprite = new SpriteSheet(spriteSheet);
	}
	
	public abstract void tick ();
	public abstract void render(Graphics g);
	//0, 0, 839, 502
	
	
	
        public boolean isOnGround(){
            return getY() + getHeight() >= Game.FRAME_HEIGHT;
        }
        
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return VelX;
	}

	public void setVelX(double velX) {
		VelX = velX;
	}

	public double getVelY() {
		return VelY;
	}

	public void setVelY(double velY) {
		VelY = velY;
	}

	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}
        
        public ID getId() {
		return id;
	}
	
        public void setId(ID id) {
                this.id = id;
        }
        
        public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public Rectangle boundsLeft(){
		return new Rectangle((int)x, (int)y, 1, (int)height);
	}
	public Rectangle boundsRight(){
		return new Rectangle((int)(x+width) - 1, (int)y, 1, (int)height);
	}
	public Rectangle boundsTop(){
		return new Rectangle((int)x, (int)y, (int)width, 1);
	}
	public Rectangle boundsBottom(){
		return new Rectangle((int)x, (int)(x+height) - 1, (int)width, 1);
	}
}

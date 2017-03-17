import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends EntityObject{
    
    public Player(double x, double y, double width, double height, double VelX, double VelY, ID id, BufferedImage spriteSheet) {
        super(x, y, width, height, VelX, VelY, ID.Player, spriteSheet);
    }
    
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean jumping = false;
    private final int JUMP_HEIGHT = 7;
    private int tempJump = 0;
    
    private final Animation walkLeft = new Animation(new BufferedImage[]{sprite.grabImage(5, 1), sprite.grabImage(6, 1), sprite.grabImage(7, 1)}, (int)(width/5));
    private final Animation walkRight = new Animation(new BufferedImage[]{sprite.grabImage(2, 1), sprite.grabImage(3, 1), sprite.grabImage(4, 1)}, (int)(width/5));
    private final Animation standing = new Animation(new BufferedImage[]{sprite.grabImage(1, 1)}, 0);
    private Animation animation = standing;
    
    @Override
    public void tick() {
        if(jumping){
            tempJump++;
            if(tempJump < JUMP_HEIGHT)setVelY(-20);
        }else{
            tempJump = 0;
            jumping = false;
        }
        
        movingRight = VelX > 0;
        movingLeft = VelX < 0;
        
        if(movingLeft)animation = walkLeft;
        else if(movingRight)animation = walkRight;
        else animation = standing;
        animation.update();
        
        x += VelX;
        y += VelY;
    }


    @Override
    public void render(Graphics g) {
      g.drawImage(animation.getSprite(), (int)x, (int)y, null);
    }   
    
    
    public void jumping(Boolean jump){
        jumping = jump && isOnGround();
        
    }
    
    
    
}

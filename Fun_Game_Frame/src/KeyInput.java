import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{
	Game game;
        Handler handler;
	boolean[] keys = new boolean[40];
        
	public KeyInput(Game game, Handler handler){
		this.game = game;
                this.handler = handler;
	}
	
	
	public void keyPressed(KeyEvent e){
            try{
                keys[e.getKeyCode()] = true;
            }catch(Exception er){}
        }
        
	
	
	public void keyReleased(KeyEvent e){
           try{
                keys[e.getKeyCode()] = false;
            }catch(Exception er){}
	}	
        
        public void update(){
            if(keys[KeyEvent.VK_ESCAPE])game.remoteStop();
            
            if(keys[KeyEvent.VK_UP])game.getPlayer().jumping(true);
            else game.getPlayer().jumping(false);
            
            if(keys[KeyEvent.VK_LEFT])game.getPlayer().setVelX(-5);
            else if(keys[KeyEvent.VK_RIGHT])game.getPlayer().setVelX(5);
            else game.getPlayer().setVelX(0);
        }
        
}
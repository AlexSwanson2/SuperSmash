import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * @author Alex Swanson
 */
public class Game extends Canvas implements Runnable {
	
	
    ////////Variables////////////
    private static final long serialVersionUID = 1L;
    public static final String TITLE = "Fun Game!";
    public static final int FRAME_WIDTH = 840;
    public static final int FRAME_HEIGHT = 525;
    boolean running = false;
    private Thread thread;

    private BufferedImage spriteSheet;

    Handler handler;
    FileRW frw = new FileRW();	
    STATE gameState = STATE.Game;
    KeyInput keyIn = new KeyInput(this, handler);

    Player player;

//    int startTopScore;
//    int newTopScore;
//    int currentScore = 1;
//    int scoreTick = 0;

    ///////////INITIALIZE////////////
    public void init(){ 
            BufferedImageLoader loader = new BufferedImageLoader();
            try{ 
                spriteSheet = loader.loadImage("Assets/Character.png");
            }
            catch(IOException e){e.printStackTrace();}

            handler = new Handler();

//            currentScore = 0;
//            try {
//                startTopScore = frw.fileRead();
//            } catch (IOException e1) {
//                startTopScore = 1;
//            }
//            newTopScore = startTopScore;

            player = new Player(100, 100, 64, 128, 0, 0, ID.Player, spriteSheet);
            handler.addObject(player);

            addKeyListener(keyIn);
    }

    ///////////Thread & Start Stuff/////////
    private synchronized void start(){ 
            if(running){
                    return;
            }
            thread = new Thread(this);
            thread.start();
            running=true;		
    }

    private synchronized void stop() { 
            if(!running){
                    return;
            }
            running=false;	
            save();
            System.exit(1);
    }



    //////Game Loop/////////
    public void run(){
            init();
            long lastTime = System.nanoTime();
            final double amountofTicks = 60.0;
            double ns = 1000000000 / amountofTicks;
            double delta = 0;
            int updates = 0;
            int frames = 0;
            long timer = System.currentTimeMillis();

            while (running){
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    if (delta >= 1){
                            tick();
                            updates++;
                            delta--;
                    }
                    render();
                    frames++;

                    if(System.currentTimeMillis() - timer > 1000){
                            timer += 1000;
                            System.out.println(frames + " FPS, Ticks " + updates);
                            updates=0;
                            frames=0;	
                    }
            }
            stop();
    }

    
    ////////Tick & Render/////////////
    private void tick(){
        handler.tick();
        keyIn.update();
        
    }

    private void render(){
            BufferStrategy bs = this.getBufferStrategy();
            if(bs==null){
                    createBufferStrategy(3);
                    return;
            }
            Graphics g = bs.getDrawGraphics();

            ///////////////

            g.setColor(Color.darkGray);
            g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
            handler.render(g);

            //////////////
            g.dispose();
            bs.show();
    }
    ///////Methods///////


    public void save(){
//            if(currentScore > startTopScore){
//                    try {
//                            frw.fileWrite(newTopScore);
//                    } catch (IOException e){
//                            System.out.println("Couldn't write to file");
//                    }
//            }
    }

    //////////Main/////////////
    public static void main(String[] args){
        Game game = new Game();
        
        game.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        JFrame frame = new JFrame(TITLE);        
        frame.setResizable(false);
        frame.getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.requestFocus();
        game.requestFocus();
        game.start();
    }


///////////////////////Getters and Setters////////////////// 	


    public Player getPlayer(){
        return player;
    }

    public void setgameState(STATE state){
            gameState = state;
    }

//    public void setCurrentScore(int currentScore) {
//            this.currentScore = currentScore;
//    }
//
//    public int getCurrentScore() {
//            return currentScore;
//    }
//    
//    public void setScoreTick(int scoreTick) {
//            this.scoreTick = scoreTick;
//    }


    public void remoteStop() {
            stop();
    }

    



    public Rectangle getLineTop(){
            return new Rectangle(0, 0, FRAME_WIDTH, 1);
    }
    public Rectangle getLineBottom(){
            return new Rectangle(0, FRAME_HEIGHT, FRAME_WIDTH, 1);
    }
    public Rectangle getLineRight(){
            return new Rectangle(FRAME_WIDTH, 0, 1, FRAME_HEIGHT);
    }
    public Rectangle getLineLeft(){
            return new Rectangle(0, 0, 1, FRAME_HEIGHT);
    }
	
 
}
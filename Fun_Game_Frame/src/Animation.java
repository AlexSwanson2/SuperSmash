import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Animation {

    private int frameCount = 0;                 // Counts ticks for change
    private final int frameDelay;                 // frame delay 1-12 (You will have to play around with this)
    private int currentFrame = 0;               // animations current frame
    private int animationDirection = 1;         // animation direction (i.e counting forward or backward)
    private final int totalFrames;                // total amount of frames for your animation

    private boolean stopped;                // has animations stopped

    private final List<Frame> frames = new ArrayList();    // Arraylist of frames 

    public Animation(BufferedImage[] frames, int frameDelay) {
        for (BufferedImage frame : frames)this.frames.add(new Frame(frame, frameDelay));

        this.frameDelay = frameDelay;
        totalFrames = this.frames.size();
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    public void update() {
        if(totalFrames <= 1)return;
        
        frameCount++;

        if (frameCount > frameDelay) {
            frameCount = 0;
            currentFrame += animationDirection;

            if (currentFrame == totalFrames - 1) {
                animationDirection = -1;
            }else if (currentFrame == 0) {
                animationDirection = 1;
            }
        }
    }
}
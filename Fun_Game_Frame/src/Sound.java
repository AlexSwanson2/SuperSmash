import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class Sound {
    Clip clip;
    AudioInputStream audioIn;
    
    public Sound(File file){
        try{
            audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){}
    }
    
    public void playSound(){
        clip.start();
    }
}

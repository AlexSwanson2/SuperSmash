import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class Sound {
    private Clip clip;
    private AudioInputStream audioIn;
    private File file;
    
    public Sound(File file){
        this.file = file;
        soundReset();
    }
    
    public final void soundReset(){
         try{
            audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){}
    }
    
    public void playSound(){
        clip.start();
        soundReset();
    }
}

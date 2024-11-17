package resourceLoader;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader {
    private File baseSound; 
    private Clip currentSound;
    
    public SoundLoader(){
        this.baseSound = loadSound(baseSound, "shoot.wav");
        
    }
    
    private File loadSound(File soundToLoad, String soundName){
        
        try {
            soundToLoad = new File(getClass().getResource("/resources/sounds/" + soundName).getFile());
        } catch (Exception e) {
            System.err.println("Unable to load sounds: " + e.getMessage());
        }
        
        return soundToLoad;
    }
    
     public void playSound(File sound) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound);
            currentSound = AudioSystem.getClip();
            currentSound.open(audioStream);
            currentSound.start();
        } catch (Exception e) {
            System.err.println("Unable to play sound: " + e.getMessage());
        }
        return;
    }

    public void stopSound(){
        if(currentSound != null){
            currentSound.stop();
            currentSound.close();
        }
    }
    
    public File getBaseSound(){
        return this.baseSound;
    }
    
}

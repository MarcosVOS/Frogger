package resourceLoader;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundLoader {
    private File baseSound; 
    private File songCar;
    private File hopFrogger;
    private Clip currentSound;
    
    public SoundLoader(){
        this.baseSound = loadSound("shoot.wav");
        this.songCar = loadSound("songCar.wav");
        this.hopFrogger = loadSound("sound-frogger-hop.wav");
        
    }
    
    private File loadSound(String soundName){
        File sound = null;  
        try {
            sound = new File(getClass().getResource("/resources/sounds/" + soundName).getFile());
        } catch (Exception e) {
            System.err.println("Unable to load sounds: " + e.getMessage());
        }
        
        return sound;
    }
    
     public void playSound(File sound) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound);
            currentSound = AudioSystem.getClip();
            currentSound.open(audioStream);
            currentSound.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println("Unable to play sound: " + e.getMessage());
        }
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
    
    public File getSongCar() {
        return this.songCar;
    }

    public File getHopFrogger() {
        return this.hopFrogger;
    }
}

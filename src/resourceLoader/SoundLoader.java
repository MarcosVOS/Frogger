package resourceLoader;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundLoader {
    private File baseSound;
    private File songCar;
    private File hopFrogger;
    private Clip currentSound;

    public SoundLoader() {
        this.baseSound = loadSound("shoot.wav");
        this.songCar = loadSound("SongCar.wav");
        this.hopFrogger = loadSound("sound-frogger-hop.wav");
    }

    private File loadSound(String soundName) {
        File soundFile = null;
        try {
            soundFile = new File(getClass().getClassLoader().getResource("resources/sounds/" + soundName).toURI());
            //System.out.println("Loaded sound: " + soundFile.getAbsolutePath()); // Para debug
        } catch (Exception e) {
            System.err.println("Unable to load sound: " + e.getMessage());
        }
        return soundFile;
    }

    public void playSound(File sound) {
        try {
            if (sound != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound);
                currentSound = AudioSystem.getClip();
                currentSound.open(audioStream);
                currentSound.start();
            } else {
                System.err.println("Sound file is null!");
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println("Unable to play sound: " + e.getMessage());
        }
    }

    public void stopSound() {
        if (currentSound != null) {
            currentSound.stop();
            currentSound.close();
        }
    }

    public File getBaseSound() {
        return this.baseSound;
    }

    public File getSongCar() {
        return this.songCar;
    }

    public File getHopFrogger() {
        return this.hopFrogger;
    }
}

package game;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    protected static SoundManager uniqueInstance;
    private Map<String, Clip> clips;
    private String soundPath = "sound/";
    
    private SoundManager() {
        clips = new HashMap<>();
    }

    public static SoundManager instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SoundManager();
        }
        return uniqueInstance;
    }
    
    public void playSound(String soundFile) {
        try {
            File file = new File(soundPath + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error al reproducir el sonido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void playLoopingSound(String soundFile) {
        try {
            File file = new File(soundPath + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clips.put(soundFile, clip);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error al reproducir el sonido en loop: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pauseAllSounds() {
        for (Clip clip : clips.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

    public void resumeAllSounds() {
        for (Clip clip : clips.values()) {
            clip.start();
        }
    }
    
    public void removeAllSounds() {
        for (Clip clip : clips.values()) {
            if (clip != null) {
                clip.stop();
                clip.close();
            }
        }
        clips.clear();
    }
}

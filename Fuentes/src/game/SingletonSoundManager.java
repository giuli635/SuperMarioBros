package game;

import javax.sound.sampled.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SingletonSoundManager {
    protected static SingletonSoundManager uniqueInstance;
    protected Map<String, Clip> clips;
    protected String soundPath = "sound/";
    
    private SingletonSoundManager() {
        clips = new HashMap<>();
    }

    public static SingletonSoundManager instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonSoundManager();
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

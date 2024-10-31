package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entities.updateables.UpdatableEntity;
import graphics.GameOverScreen;
import utils.KeyStatus;

public class Game implements WindowListener, KeyListener {
    protected static int SECOND = 1000;
    protected static int FPS = 60;
    protected static Game uniqueInstance;
    protected Set<UpdatableEntity> toUpdateRegistry;
    protected Map<Integer, KeyStatus> keysStatus;
    protected LevelStats lvlStats;
    protected boolean run;
    protected boolean pause;
    protected boolean pauseKeyAlreadyPressed = false;
    protected String[] levels = {"menu.txt", "level1.txt", "level2.txt", "level3.txt"};
    protected int currLevel = 1;
    protected long frames = 0;

    protected List<UpdatableEntity> toAddList = new ArrayList<>();
    protected List<UpdatableEntity> toRemoveList = new ArrayList<>();
    protected boolean debugging;
    protected boolean reset;
    protected GameOverScreen gameOverScreen;

    public boolean isDebugging() {
        return debugging;
    }

    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
    }

    private Game() {
        toUpdateRegistry = new HashSet<>();
        keysStatus = new HashMap<>();
        lvlStats = null;
        run = true;
        pause = false;
        reset = false;
    }

    public static Game instance() {
        return uniqueInstance;
    }

    public void registerToUpdate(UpdatableEntity e) {
        toUpdateRegistry.add(e);
    }

    public void unregisterToUpdate(UpdatableEntity e) {
        toUpdateRegistry.remove(e);
    }

    public KeyStatus getKeyStatus(int key) {
        return keysStatus.getOrDefault(key, KeyStatus.RELEASED);
    }

    private void loop() {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        graphicEngine.initBackgrounds();
        SoundManager soundManager = SoundManager.instance();
        LevelReader reader = LevelReader.instance();
        lvlStats = reader.createLevel(3, 300, currLevel, 0);
        reader.readTxt(levels[currLevel]);
        soundManager.playLoopingSound("marioBackground.wav");
        long lastUpdateTime;
        while (run) {
            debugging = false;
            lastUpdateTime = System.currentTimeMillis();
            if(!pause) {
                List<UpdatableEntity> list = new ArrayList<>(toUpdateRegistry);
                
                for (UpdatableEntity entity : list) {
                    entity.update();
                }
                CollisionsEngine.instance().update();

                if (!debugging) {
                    try {
                        Thread.sleep(SECOND / FPS - (lastUpdateTime - System.currentTimeMillis()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                graphicEngine.drawFrame();
                frames++;

                if (reset) {
                    toUpdateRegistry = new HashSet<>();
                    CollisionsEngine.instance().reset();
                    graphicEngine.reset();
                    if (!checkGameOver(graphicEngine, soundManager, reader)){
                        lvlStats = reader.createLevel(lvlStats.getLives(), lvlStats.getRemainingTime(), lvlStats.getLevelNumber(), 0);
                        reader.readTxt(levels[currLevel]);
                        soundManager.playLoopingSound("marioBackground.wav");
                        reset = false;
                    }
                }
            }

            checkPause();
        }
    }

    public boolean checkGameOver(GraphicEngine graphicEngine, SoundManager soundManager, LevelReader reader){
        if (lvlStats.getLives() == 0){
            gameOverScreen = new GameOverScreen();
            gameOverScreen.add();
            graphicEngine.setDepth(gameOverScreen, GraphicEngine.FRONT_DEPTH + 1);
            graphicEngine.drawFrame();
            soundManager.playSound("gameover.wav");
            graphicEngine.remove(gameOverScreen);
            try {
                Thread.sleep(4000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lvlStats = reader.createLevel(3, 300, 1, 0);
            reader.readTxt(levels[currLevel]);
            soundManager.playLoopingSound("marioBackground.wav");
            reset = false;
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        uniqueInstance = new Game();
        uniqueInstance.loop();
    }

    public void resetCurrentLevel() {
        reset=true;
    }

    public void advanceLevel() {
        if (currLevel < levels.length -1) {
            currLevel ++;
            SoundManager.instance().removeAllSounds();
            LevelStats stash = new LevelStats(300, lvlStats.getLives(), currLevel, lvlStats.getScore());
            resetCurrentLevel();
            lvlStats = stash;
        }
    }
    
    public LevelStats getLevelStats(){
        return lvlStats;
    }

    public long getFrames() {
        return frames;
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        run = false;
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        keysStatus.put(arg0.getKeyCode(), KeyStatus.PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        keysStatus.put(arg0.getKeyCode(), KeyStatus.RELEASED);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    public void checkPause() {
        if (Game.instance().getKeyStatus(KeyEvent.VK_P) == KeyStatus.PRESSED) {
            if (!pauseKeyAlreadyPressed) {
                pause = !pause;
                pauseKeyAlreadyPressed = true;
                if (pause){
                    lvlStats.pauseTimer();
                    SoundManager.instance().pauseAllSounds();
                }
                else{
                    lvlStats.resumeTimer();
                    SoundManager.instance().resumeAllSounds();
                }

            }
        } else {
            pauseKeyAlreadyPressed = false;
        }
    }
}

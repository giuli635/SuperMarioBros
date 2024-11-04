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

import entities.updateables.UpdateableEntity;
import graphics.RankingScreen;
import graphics.TemporaryScreenOverlay;
import utils.KeyStatus;

public class Game implements WindowListener, KeyListener {
    protected static int SECOND = 1000;
    protected static int FPS = 60;
    protected static Game uniqueInstance;
    protected Set<UpdateableEntity> toUpdateRegistry;
    protected Map<Integer, KeyStatus> keysStatus;
    protected Stats stats;
    protected Runnable executionState;

    protected boolean pause;
    protected boolean runGameLoop;
    protected boolean runGame;
    protected boolean debugging;

    protected String[] levels = {"menu.txt", "level1.txt", "level2.txt", "level3.txt"};
    protected long frames = 0;

    protected List<UpdateableEntity> toAddList = new ArrayList<>();
    protected List<UpdateableEntity> toRemoveList = new ArrayList<>();

    protected RankingManager rankingManager;

    public boolean isDebugging() {
        return debugging;
    }

    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
    }

    private Game() {
        toUpdateRegistry = new HashSet<>();
        keysStatus = new HashMap<>();
        stats = null;
        runGame = true;
        runGameLoop = true;
        pause = false;
        rankingManager = new RankingManager();
    }

    public static Game instance() {
        return uniqueInstance;
    }

    public void registerToUpdate(UpdateableEntity e) {
        toUpdateRegistry.add(e);
    }

    public void unregisterToUpdate(UpdateableEntity e) {
        toUpdateRegistry.remove(e);
    }

    public KeyStatus getKeyStatus(int key) {
        return keysStatus.getOrDefault(key, KeyStatus.RELEASED);
    }

    protected void runGame() {
        stats = new Stats(300, 3, 0, 0);
        reloadGameStatus();

        while (runGame) {
            executionState.run();
        }
    }

    protected void loop() {
        long lastUpdateTime;
        while (runGameLoop) {
            debugging = false;
            lastUpdateTime = System.currentTimeMillis();
            List<UpdateableEntity> list = new ArrayList<>(toUpdateRegistry);
            
            for (UpdateableEntity entity : list) {
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
            GraphicEngine.instance().drawFrame();
            frames++;
        }

        // if (pause) {
        //     activatePause();
        // }
    }

    public void advanceLevel() {
        reload();

        if (stats.getLevelNumber() < levels.length - 1) {
            if (stats.getLevelNumber() > 0){
                stats.modifyPoints(stats.getRemainingTime() * 50);
            }

            stats.advanceLevel();
        } else {
            new TemporaryScreenOverlay("gameEnd", 4 * SECOND).add();
            checkRanking();
            stats.reset();
        }
    }

    public void checkGameOver() {
        reload();

        if (stats.getLives() == -1){
            new TemporaryScreenOverlay("gameOver", 4 * SECOND).add();
            checkRanking();
            stats.reset();
        }
    }

    public void reloadGameStatus(){
        toUpdateRegistry = new HashSet<>();
        GraphicEngine.instance().initBackgrounds();
        // SoundManager.instance().removeAllSounds();
        LevelReader.instance().loadLevel(levels[stats.getLevelNumber()]);
        LevelReader.instance().loadStats(stats);
        // SoundManager.instance().playLoopingSound("marioBackground.wav");
        runGameLoop = true;
        executionState = new Runnable() {
            public void run() {
                loop();
            }
        };
    }

    protected synchronized void activatePause() {
        stats.pauseTimer();
        SoundManager.instance().playSound("pause.wav");
        SoundManager.instance().pauseAllSounds();

        stats.resumeTimer();
        SoundManager.instance().resumeAllSounds();
        loop();
    }

    public void checkRanking() {
        int currentScore = stats.getScore();
        if (rankingManager.checkAndUpdateRanking(currentScore)) {
            showRanking();
        }
    }

    public void showRanking(){
        RankingScreen rankingScreen = new RankingScreen(rankingManager);
        rankingScreen.add();
    }

    public static void main(String[] args) {
        uniqueInstance = new Game();
        uniqueInstance.runGame();
    }

    public void reload() {
        executionState = new Runnable() {
            public void run() {
                reloadGameStatus();
            }
        };

        runGameLoop = false;
    }

    public void resume() {
    }

    public void pause() {
    }

    protected void togglePause() {
    }

    public Stats getLevelStats(){
        return stats;
    }

    public long getFrames() {
        return frames;
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        runGame = false;
        runGameLoop = false;
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
        if (arg0.getKeyCode() != KeyEvent.VK_P) {
            keysStatus.put(arg0.getKeyCode(), KeyStatus.PRESSED);
        } else {
            togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        keysStatus.put(arg0.getKeyCode(), KeyStatus.RELEASED);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}

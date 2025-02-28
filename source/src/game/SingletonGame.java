package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import entities.updateables.ObserverUpdateableEntity;
import graphics.RankingScreen;
import graphics.TemporaryScreenOverlay;
import utils.KeyStatus;

public class SingletonGame implements WindowListener, KeyListener {
    public static final int SECOND = 1000;
    public static final int FPS = 60;
    protected final Object lock = new Object();
    protected static SingletonGame uniqueInstance;
    protected Set<ObserverUpdateableEntity> toUpdateRegistry;
    protected Map<Integer, KeyStatus> keysStatus;
    protected Stats stats;
    protected Queue<Runnable> executionQueue;

    protected boolean pause;
    protected boolean runGameLoop;
    protected boolean runGame;
    protected boolean debugging;

    protected String[] levels = {"menu.txt", "level1.txt", "level2.txt", "level3.txt"};
    protected long frames = 0;

    protected List<ObserverUpdateableEntity> toAddList = new ArrayList<>();
    protected List<ObserverUpdateableEntity> toRemoveList = new ArrayList<>();

    protected RankingManager rankingManager;

    private SingletonGame() {
        toUpdateRegistry = new HashSet<>();
        keysStatus = new HashMap<>();
        stats = null;
        runGame = true;
        runGameLoop = true;
        pause = false;
        rankingManager = new RankingManager();
        executionQueue = new LinkedList<>();
    }

    public static SingletonGame instance() {
        return uniqueInstance;
    }

    public void registerToUpdate(ObserverUpdateableEntity e) {
        toUpdateRegistry.add(e);
    }

    public void unregisterToUpdate(ObserverUpdateableEntity e) {
        toUpdateRegistry.remove(e);
    }

    public KeyStatus getKeyStatus(int key) {
        return keysStatus.getOrDefault(key, KeyStatus.RELEASED);
    }

    protected void runGame() {
        stats = new Stats(300, 3, 0, 0);
        reloadGameStatus();

        while (runGame) {
            executionQueue.remove().run();
        }

        System.exit(0);
    }

    protected void loop() {
        long lastUpdateTime;
        while (runGameLoop) {
            debugging = false;
            lastUpdateTime = System.currentTimeMillis();
            List<ObserverUpdateableEntity> list = new ArrayList<>(toUpdateRegistry);
            
            for (ObserverUpdateableEntity entity : list) {
                entity.update();
            }
            SingletonCollisionsEngine.instance().update();

            if (!debugging) {
                try {
                    Thread.sleep(SECOND / FPS - (lastUpdateTime - System.currentTimeMillis()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SingletonGraphicEngine.instance().drawFrame();
            frames++;
        }
    }

    public void advanceLevel() {
        runGameLoop = false;

        executionQueue.add(new Runnable() {
            public void run() {
                if (stats.getLevelNumber() < levels.length - 1) {
                    if (stats.getLevelNumber() > 0){
                        stats.modifyPoints(stats.getRemainingTime() * 50);
                    }

                    stats.advanceLevel();
                } else {
                    SingletonSoundManager.instance().removeAllSounds();
                    SingletonSoundManager.instance().playSound("worldClear.wav");
                    new TemporaryScreenOverlay("gameEnd", 4 * SECOND).add();
                    checkRanking();
                    stats.reset();
                }

                reload();
            }
        });
    }

    public void checkGameOver() {
        runGameLoop = false;

        executionQueue.add(new Runnable() {
            public void run() {
                if (stats.getLives() == 0){
                    SingletonSoundManager.instance().playSound("gameover.wav");
                    new TemporaryScreenOverlay("gameOver", 4 * SECOND).add();
                    checkRanking();
                    stats.reset();
                }

                reload();
            }
        });
    }

    public void reloadGameStatus(){
        toUpdateRegistry = new HashSet<>();
        SingletonGraphicEngine.instance().initBackgrounds();
        SingletonSoundManager.instance().removeAllSounds();
        LevelReader.instance().loadLevel(levels[stats.getLevelNumber()]);
        LevelReader.instance().loadStats(stats);
        SingletonSoundManager.instance().playLoopingSound("marioBackground.wav");
        resumeLoop();
    }

    public void checkRanking() {
        int currentScore = stats.getScore();
        if (rankingManager.checkAndUpdateRanking(currentScore)) {
            showRanking();
        }
    }

    public void showRanking(){
        pause();
        RankingScreen rankingScreen = new RankingScreen(rankingManager);
        rankingScreen.add();
    }

    public static void main(String[] args) {
        uniqueInstance = new SingletonGame();
        uniqueInstance.runGame();
    }

    protected void resumeLoop() {
        runGameLoop = true;

        executionQueue.add(new Runnable() {
            public void run() {
                loop();
            };
        });
    };

    public void reload() {
        runGameLoop = false;

        executionQueue.add(new Runnable() {
            public void run() {
                reloadGameStatus();
            }
        });
    }

    public void resume() {
        if (pause) {
            synchronized (lock) {
                lock.notify();
            }

            stats.resumeTimer();
            SingletonSoundManager.instance().resumeAllSounds();
            pause = false;
        }
    }

    public void pause() {
        stats.pauseTimer();
        SingletonSoundManager.instance().playSound("pause.wav");
        SingletonSoundManager.instance().pauseAllSounds();
        runGameLoop = false;
        pause = true;

        executionQueue.add(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                        resumeLoop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected void togglePause() {
        if (pause) {
            resume();
        } else {
            pause();
        }
    }

    public boolean isDebugging() {
        return debugging;
    }

    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
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

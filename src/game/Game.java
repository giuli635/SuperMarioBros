package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import entities.Entity;
import entities.Mario;

public class Game implements WindowListener, KeyListener {
    protected static int SECOND = 1000;
    protected static int FPS = 60;
    protected static Game uniqueInstance;
    protected Set<Entity> toUpdateRegistry;
    protected Map<Integer, KeyStatus> keysStatus;
    protected Level currLevel;
    protected Mario mario;
    protected boolean run;

    private Game() {
        toUpdateRegistry = new HashSet<>();
        keysStatus = new HashMap<>();
        currLevel = null;
        mario = null;
        run = true;
    }

    public static Game instance() {
        return uniqueInstance;
    }

    public void registerToUpdate(Entity e) {
        toUpdateRegistry.add(e);
    }

    public void unregisterToUpdate(Entity e) {
        toUpdateRegistry.remove(e);
    }

    public KeyStatus getKeyStatus(int key) {
        return keysStatus.getOrDefault(key, KeyStatus.RELEASED);
    }

    private void loop() {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        LevelReader reader = LevelReader.instance();
        reader.createLevel("nivel1.txt");
        long lastUpdateTime;
        while (run) {
            lastUpdateTime = System.currentTimeMillis();
            for (Entity entity : toUpdateRegistry) {
                entity.update();
            }

            CollisionsEngine.instance().checkCollisions();

            try {
                Thread.sleep(SECOND / FPS - (lastUpdateTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            graphicEngine.drawFrame();
        }
    }

    public static void main(String[] args) {
        uniqueInstance = new Game();
        uniqueInstance.loop();
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
}

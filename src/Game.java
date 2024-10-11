import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Set;
import java.util.TreeSet;

public class Game implements WindowListener {
    protected static int SECOND = 1000;
    protected static int FPS = 60;
    protected static Game uniqueInstance;
    protected Set<Entity> toUpdateRegistry;
    protected Level currLevel;
    protected Mario mario;
    protected boolean run;

    private Game() {
        toUpdateRegistry = new TreeSet<>();
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

    public boolean getKeyPressed(int key) {
        return false;
    }

    public boolean getKeyReleased(int key) {
        return false;
    }

    private void loop() {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        long lastUpdateTime = System.currentTimeMillis();
        long newUpdateTime;
        while (run) {
            newUpdateTime = System.currentTimeMillis();
            if (newUpdateTime - lastUpdateTime > (SECOND / FPS)) {
                lastUpdateTime = newUpdateTime;
                graphicEngine.drawFrame();
            }
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
}

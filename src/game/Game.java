package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import entities.Mario;
import entities.UpdatableEntity;
import graphics.TimerGraphicElement;

public class Game implements WindowListener, KeyListener {
    protected static int SECOND = 1000;
    protected static int FPS = 60;
    protected static Game uniqueInstance;
    protected Set<UpdatableEntity> toUpdateRegistry;
    protected Map<Integer, KeyStatus> keysStatus;
    protected Level currLevel;
    protected Mario mario;
    protected boolean run;
    protected boolean pause;
    private boolean pauseKeyAlreadyPressed = false;
    protected String mode; 
    private LevelTimer levelTimer; // Agregamos el temporizador
    private TimerGraphicElement timerGraphicElement; // Elemento gráfico del temporizador


    private Game() {
        toUpdateRegistry = new HashSet<>();
        keysStatus = new HashMap<>();
        currLevel = null;
        mario = null;
        run = true;
        pause = false;
        mode = "mode1";
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

    public String getMode(){
        return mode;
    }

    private void loop() {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        LevelReader reader = LevelReader.instance();
        reader.createLevel("nivel1.txt");
        long lastUpdateTime;
        levelTimer = new LevelTimer(300);
        timerGraphicElement = new TimerGraphicElement(levelTimer); // Crear elemento gráfico
        GraphicEngine.instance().addGraphicElement(timerGraphicElement); // Añadirlo a la pantalla
        while (run) {
            lastUpdateTime = System.currentTimeMillis();
            
            if(!pause) {
                for (UpdatableEntity entity : toUpdateRegistry) {
                    entity.update();
                }
                CollisionsEngine.instance().update();
            }

            checkPause();

            try {
                Thread.sleep(SECOND / FPS - (lastUpdateTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            graphicEngine.drawFrame();
        }
        levelTimer.stopTimer();
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

    public void checkPause() {
        // Verificar si la tecla P está presionada
        if (Game.instance().getKeyStatus(KeyEvent.VK_P) == KeyStatus.PRESSED) {
            // Solo cambiar el estado de pausa si la tecla P no estaba previamente presionada
            if (!pauseKeyAlreadyPressed) {
                pause = !pause; // Cambiar el estado de pausa
                pauseKeyAlreadyPressed = true; // Registrar que la tecla P ya está presionada
            }
        } else {
            // Si la tecla no está presionada, restablecer la bandera
            pauseKeyAlreadyPressed = false;
        }
    }

    
}

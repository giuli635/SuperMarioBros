
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

            try {
                Thread.sleep(SECOND / FPS - (lastUpdateTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            graphicEngine.drawFrame();
            scrollScreen();
        }
    }

    public void scrollScreen() {  
        // Deberia dejar de moverse al llegar al final pero no AHHHH//
        int screenX = Screen.instance().getX();
        Dimension screenSize = GraphicEngine.instance().getPanelSize();
        int screenWidth = screenSize.width;
        int screenRight = screenWidth + screenX;
        int levelEnd = CollisionsEngine.instance().pixelChunkCount();
        if(screenX > 0 && screenRight != levelEnd) {
            CollisionsEngine collisionsEngine = CollisionsEngine.instance();
             for(int i=0;i<collisionsEngine.getAmountOfChunks();i++) {
                for (Collider collider : collisionsEngine.getChunk(i)) {
                
                //collider.translate(-screenX, 0);
                GraphicElement graphicElement = collider.getEntity().getGraphicElement();
                graphicElement.translate(-screenX, 0);
                
                }
            }
        }
        
        
        
        
    }

    public void scrollScreenBackWards() { //ESTE METODO ES REDUNDANTE, lo dejo por dejar nunca va a volver la pantalla hacia atras//
        //EL SIGUIENTE CODIGO NO HACE USO DEL SIGLETON SCREEN PARA EL MOVIMIENTO DE LA CAMARA//
        //SI SE QUIERE USAR MARIO DEBE LLAMAR A ESTE METODO DESDE UPDATE() AL MOVERSE A LA IZQUIERDA//
        
        /* 
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        int scrollAmount = 6; 
        for(int i=0;i<collisionsEngine.getAmountOfChunks();i++) {
            for (Collider collider : collisionsEngine.getChunk(i)) {
                
                collider.translate(scrollAmount,0);
                GraphicElement graphicElement = collider.getEntity().getGraphicElement();
                graphicElement.translate(scrollAmount, 0);
                
            }
        }
        
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        int scrollAmount = 6; 
        for(int i=0;i<collisionsEngine.getAmountOfChunks();i++) {
            for (Collider collider : collisionsEngine.getChunk(i)) {
                
                collider.translate(-scrollAmount,0);
                GraphicElement graphicElement = collider.getEntity().getGraphicElement();
                graphicElement.translate(-scrollAmount, 0);
                
            }
        }
        */    
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

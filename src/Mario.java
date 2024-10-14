import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Mario extends GameEntity {
    protected Point position;
    protected int speedX; 
    protected int lifes;
    protected boolean loaded;

    public Mario() {
        super();
        speedX = 6;
        position = collider.getPosition();
        graphicElement.setSprite(new ImageIcon("sprites/mario.png"));
    }

    public Entity clone() {
        return new Mario();
    }

    public void update() {
        
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            graphicElement.translate(speedX, 0);
            collider.translate(speedX, 0);
            
        } else if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            graphicElement.translate(-speedX, 0);
            collider.translate(-speedX, 0);
            
        }
        //se actualiza la camara junto con mario, se pasa por parametro el final del nivel, o eso deberia...
        updateScreenPosition(CollisionsEngine.instance().pixelChunkCount());

    }

    private void updateScreenPosition(int levelWidth) {
        
        Point marioPosition = graphicElement.getPosition();
        int marioX = (int)marioPosition.getX();
    
       
        Dimension screenSize = GraphicEngine.instance().getPanelSize();
        int screenWidth = screenSize.width;
    
        
        int maxCameraX = levelWidth - screenWidth;
    
        //se ajusta la camara para que valla con mario y se deja de mover al llegar al final del nivel, o eso deberia...

        if (marioX > screenWidth / 2 && marioX < levelWidth - screenWidth / 2) {
            
            Screen.instance().setX(marioX - screenWidth / 2);
        } else if (marioX >= levelWidth - screenWidth / 2) {
           
            Screen.instance().setX(maxCameraX);
        } else {
            
            Screen.instance().setX(0);
        }
    }

}

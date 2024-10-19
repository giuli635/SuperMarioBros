package game;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import colliders.Collider;
import colliders.Direction;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import entities.Entity;
import entities.GameEntity;
import entities.Goomba;
import entities.Koopa_Troopa;
import entities.Mario;
import entities.PowerUp;
import entities.Spiny;
import graphics.GraphicElement;

public class LevelReader  {
    protected static int CHUNK = 32;
    protected static LevelReader uniqueInstance;
    protected Map<Character,Entity> prototypes;

    protected static LevelReader instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new LevelReader();
        }
        return uniqueInstance;
    }

    public Level createLevel(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            loadEntities(br);
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        loadScreen();
        return null;
    }

    private void loadScreen() {
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        GraphicEngine graphicEngine = GraphicEngine.instance();

        int lastChunkInScreen = (int) Math.ceil(graphicEngine.getPanelSize().getWidth() / (double) CHUNK);
        int windowHeight = (int) graphicEngine.getPanelSize().getHeight();
        graphicEngine.setPosition(CHUNK);

        for (Collider collider : collisionsEngine.getCollidersInRange(CHUNK + 1, (int) graphicEngine.getPanelSize().getWidth())) {
            GraphicElement graphicElement = collider.getEntity().getGraphicElement();
            Point colliderPosition = collider.getPosition();
            graphicElement.setPosition((int) colliderPosition.getX() - CHUNK, (int) colliderPosition.getY());
            graphicEngine.addGraphicElement(graphicElement);
        }
        
        ScreenBorderCollider leftBorder = new ScreenBorderCollider(
            new Rectangle(0, 0, CHUNK, windowHeight),
            Direction.LEFT
        );

        ScreenBorderCollider rightBorder = new ScreenBorderCollider(
            new Rectangle(lastChunkInScreen * CHUNK, 0, CHUNK, windowHeight),
            Direction.RIGHT
        );

        int middleChunk = lastChunkInScreen / 2;
        new ScreenDisplacementCollider(
            new Rectangle(CHUNK * middleChunk, 0, CHUNK, windowHeight),
            leftBorder,
            rightBorder
        );
    }
    
    public void loadEntities(BufferedReader br) throws IOException {
        String chunk;
        chunk = br.readLine();
        int i = 1;
        while (chunk != null) {
            for (int j = 0; j < chunk.length(); j++) {
                char item = chunk.charAt(j);
                if (item != ' ') {
                    Entity newEntity;
                    if (item == 'M') {
                        newEntity = new Mario();
                        Game.instance().registerToUpdate(newEntity);
                    } else if (item == 'g') {
                        newEntity = new Goomba();
                        Game.instance().registerToUpdate(newEntity);
                    } else if (item == 'k') {
                        newEntity = new Koopa_Troopa();
                        Game.instance().registerToUpdate(newEntity);
                    } else if (item == 'y') {
                        newEntity = new Spiny();
                        Game.instance().registerToUpdate(newEntity);
                    }
                     else if (item == 's'){
                        newEntity = new PowerUp();
                     }
                     else {
                        newEntity = new GameEntity();
                    }
                    Collider newEntityCollider = newEntity.getCollider();
                    newEntityCollider.setPosition(i * CHUNK, j * CHUNK);
                }
            }
            i++;
            chunk = br.readLine();
        }
    }

    
}

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
import entities.Mario;
import graphics.GraphicElement;

public class LevelReader  {
    protected static int CHUNK = 32;
    protected static LevelReader uniqueInstance;
    protected Map<Character,Entity> prototypes;
    protected GameEntity generalPrototype;
    protected Mario marioPrototype;

    protected LevelReader() {
        marioPrototype = new Mario();
        generalPrototype = new GameEntity();
    }

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
        double windowHeight = graphicEngine.getPanelSize().getHeight();

        for (int i = 1; i < lastChunkInScreen; i++) {
            for (Collider collider : collisionsEngine.getChunk(i)) {
                GraphicElement graphicElement = collider.getEntity().getGraphicElement();
                Point colliderPosition = collider.getPosition();
                int yPosition = (int) (windowHeight - colliderPosition.getY());
                graphicElement.setPosition((int) colliderPosition.getX() - CHUNK, yPosition);
                graphicEngine.addGraphicElement(graphicElement);
            }
        }
        
        ScreenBorderCollider leftBorder = new ScreenBorderCollider(
            new Rectangle(0, 0, CHUNK, (int) windowHeight),
            Direction.LEFT
        );

        ScreenBorderCollider rightBorder = new ScreenBorderCollider(
            new Rectangle(lastChunkInScreen * CHUNK, 0, CHUNK, (int) windowHeight),
            Direction.RIGTH
        );

        int middleChunk = lastChunkInScreen / 2;
        new ScreenDisplacementCollider(
            new Rectangle((int) (CHUNK * middleChunk), 0, CHUNK, (int) windowHeight),
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
                        newEntity = marioPrototype.clone();
                        Game.instance().registerToUpdate(newEntity);
                    } else {
                        newEntity = generalPrototype.clone();
                    }
                    Collider newEntityCollider = newEntity.getCollider();
                    newEntityCollider.setPosition(i * CHUNK, j * CHUNK);
                    newEntityCollider.setSize(CHUNK, CHUNK);
                    CollisionsEngine.instance().addToChunk(i, newEntityCollider);
                }
            }
            i++;
            chunk = br.readLine();
        }
    }

    
}

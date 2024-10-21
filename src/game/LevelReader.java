package game;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import colliders.Collider;
import colliders.Direction;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import entities.Entity;
import graphics.GraphicElement;
import loading.BlockLoader;
import loading.EntityLoader;
import loading.GoombaLoader;
import loading.KoopaTroopaLoader;
import loading.MarioLoader;
import loading.SpinyLoader;
import loading.SuperMushroomLoader;

public class LevelReader  {
    protected static int CHUNK = 32;
    protected static LevelReader uniqueInstance;
    protected Map<Character, EntityLoader> loaders;

    private LevelReader() {
        loaders = new HashMap<>();
        loaders.put('M', new MarioLoader());
        loaders.put('y', new SpinyLoader());
        loaders.put('k', new KoopaTroopaLoader());
        loaders.put('g', new GoombaLoader());
        loaders.put('s', new SuperMushroomLoader());
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
        int windowHeight = (int) graphicEngine.getPanelSize().getHeight();
        graphicEngine.setPosition(2 * CHUNK);

        for (Collider collider : collisionsEngine.getCollidersInRange(2 * CHUNK + 1, (int) graphicEngine.getPanelSize().getWidth() + CHUNK)) {
            GraphicElement graphicElement = collider.getEntity().getGraphicElement();
            Point colliderPosition = collider.getPosition();
            graphicElement.setPosition((int) colliderPosition.getX() - 2 * CHUNK, (int) colliderPosition.getY());
            graphicEngine.addGraphicElement(graphicElement);
        }
        
        ScreenBorderCollider leftBorder = new ScreenBorderCollider(
            new Rectangle(32, 0, CHUNK, windowHeight),
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
        BlockLoader blockLoader = new BlockLoader();
        chunk = br.readLine();
        int i = 2;
        while (chunk != null) {
            for (int j = 0; j < chunk.length(); j++) {
                char character = chunk.charAt(j);
                if (character != ' ') {
                    Entity newEntity = loaders.getOrDefault(character, blockLoader).load();
                    Collider newEntityCollider = newEntity.getCollider();
                    newEntityCollider.setPosition(i * CHUNK, j * CHUNK);
                }
            }
            i++;
            chunk = br.readLine();
        }
    }
}

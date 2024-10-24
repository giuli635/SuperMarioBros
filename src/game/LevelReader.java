package game;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import colliders.Collider;
import colliders.DeleterCollider;
import colliders.Direction;
import colliders.LoaderCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.UnloaderCollider;
import entities.BuzzyBeetle;
import entities.Entity;
import graphics.GraphicElement;
import loading.BlockLoader;
import loading.EntityLoader;
import loading.GoombaLoader;
import loading.KoopaTroopaLoader;
import loading.LakituLoader;
import loading.MarioLoader;
import loading.PipeLoader;
import loading.QuestionBlockLoader;
import loading.SpinyLoader;
import loading.SuperMushroomLoader;
import loading.BrickLoader;
import loading.BuzzyBeetleLoader;

public class LevelReader  {
    protected static int CHUNK = 32;
    protected static LevelReader uniqueInstance;
    protected Map<Character, EntityLoader> loaders;
    protected static int loadingStartingPoint = 6;

    private LevelReader() {
        loaders = new HashMap<>();
        loaders.put('M', new MarioLoader());
        loaders.put('y', new SpinyLoader());
        loaders.put('k', new KoopaTroopaLoader());
        loaders.put('g', new GoombaLoader());
        loaders.put('z', new BuzzyBeetleLoader());
        loaders.put('s', new SuperMushroomLoader());
        loaders.put('b', new BrickLoader());
        //loaders.put('p', new PipeLoader());
        //loaders.put('?', new QuestionBlockLoader());
        loaders.put('l', new LakituLoader());
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

        int lastChunkInScreen = (int) Math.ceil(graphicEngine.getPanelSize().getWidth() / (double) CHUNK) + loadingStartingPoint;
        int windowHeight = (int) graphicEngine.getPanelSize().getHeight();
        graphicEngine.setPosition(loadingStartingPoint * CHUNK);

        for (Collider collider : collisionsEngine.getCollidersInRange(loadingStartingPoint * CHUNK + 1, (int) graphicEngine.getPanelSize().getWidth() + CHUNK * loadingStartingPoint)) {
            GraphicElement graphicElement = collider.getEntity().getGraphicElement();
            Point colliderPosition = collider.getPosition();
            graphicElement.setPosition((int) colliderPosition.getX() - loadingStartingPoint * CHUNK, (int) colliderPosition.getY());
            graphicEngine.addGraphicElement(graphicElement);
        }

        LoaderCollider loader = new LoaderCollider(new Rectangle(0, 0, CHUNK, 2 * windowHeight));

        for (int i =  0; i <= lastChunkInScreen; i++) {
            loader.translate(CHUNK, 0);
            collisionsEngine.update();
        }

        UnloaderCollider unloader = new UnloaderCollider(new Rectangle((loadingStartingPoint - 2) * CHUNK, 0, CHUNK, 2 * windowHeight));

        DeleterCollider deleter = new DeleterCollider(new Rectangle((loadingStartingPoint - 6) * CHUNK, 0, CHUNK, 2 * windowHeight));
        
        ScreenBorderCollider leftBorder = new ScreenBorderCollider(
            new Rectangle((loadingStartingPoint - 1) * CHUNK, 0, CHUNK, windowHeight),
            Direction.LEFT
        );

        ScreenBorderCollider rightBorder = new ScreenBorderCollider(
            new Rectangle(lastChunkInScreen * CHUNK, 0, CHUNK, 2 * windowHeight),
            Direction.RIGHT
        );

        int middleChunk = lastChunkInScreen / 2;
        new ScreenDisplacementCollider(
            new Rectangle(CHUNK * middleChunk, 0, CHUNK, 2 * windowHeight),
            leftBorder,
            rightBorder,
            loader,
            unloader,
            deleter
        );
    }
    
    public void loadEntities(BufferedReader br) throws IOException {
        String chunk;
        BlockLoader blockLoader = new BlockLoader();
        chunk = br.readLine();
        int i = loadingStartingPoint - 1;
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

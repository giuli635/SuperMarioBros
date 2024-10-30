package game;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import loading.*;
import colliders.Collider;
import colliders.invisibles.DeleterCollider;
import colliders.invisibles.LoaderCollider;
import colliders.invisibles.ScreenBorderCollider;
import colliders.invisibles.ScreenDisplacementCollider;
import colliders.invisibles.GraphicUnloaderCollider;
import colliders.invisibles.LevelEndCollider;
import graphics.GameGraphicElement;
import graphics.StatsBar;
import utils.Direction;

public class LevelReader {
    public static final int CHUNK = 32;
    protected static LevelReader uniqueInstance;
    protected Map<Character, EntityLoader> loaders;
    protected static int loadingStartingPoint = 6;
    protected int column;
    protected int row;
    protected String chunk;

    private LevelReader() {
        loaders = new HashMap<>();
        loaders.put('M', new MarioLoader());
        loaders.put('y', new SpinyLoader());
        loaders.put('k', new KoopaTroopaLoader());
        loaders.put('g', new GoombaLoader());
        loaders.put('z', new BuzzyBeetleLoader());
        loaders.put('s', new SuperMushroomLoader());
        loaders.put('b', new BrickLoader());
        loaders.put('P', new TopPipeLoader());
        loaders.put('p', new BasePipeLoader());
        loaders.put('?', new QuestionBlockLoader());
        loaders.put('l', new LakituLoader());
        loaders.put('r', new PiranhaPlantLoader());
        loaders.put('>', new EmptyBlockLoader());
        loaders.put('v', new GreenMushroomLoader());
        loaders.put('C', new CastleLoader());
        loaders.put('c', new CastleLoader());
        loaders.put('e', new FlagPoleLoader());
    }

    protected static LevelReader instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new LevelReader();
        }
        return uniqueInstance;
    }

    public LevelStats createLevel(int livesMario, int levelTimer, int numberLevel, int score) {
        LevelStats level = null;
        level = new LevelStats(levelTimer, livesMario, numberLevel, score);
        StatsBar statsBar = new StatsBar(level);
        GraphicEngine.instance().add(statsBar);
        GraphicEngine.instance().setDepth(statsBar, GraphicEngine.FRONT_DEPTH);
        return level;
    }

    public void readTxt(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            loadEntities(br);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadScreen();
    }

    private void loadScreen() {
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        GraphicEngine graphicEngine = GraphicEngine.instance();

        int lastChunkInScreen = (int) Math.ceil(graphicEngine.getPanelSize().getWidth() / (double) CHUNK) + loadingStartingPoint;
        int windowHeight = (int) graphicEngine.getPanelSize().getHeight();
        graphicEngine.setPosition(loadingStartingPoint * CHUNK);

        Iterable<Collider> chunksToLoad = collisionsEngine.getCollidersInRange(
            loadingStartingPoint * CHUNK + 1,
            (int) graphicEngine.getPanelSize().getWidth() + CHUNK * loadingStartingPoint
        );

        for (Collider collider : chunksToLoad) {
            GameGraphicElement graphicElement = collider.getEntity().getGraphicElement();
            if (graphicElement != null) {
                Point colliderPosition = collider.getPosition();

                int heightDifference = graphicElement.getSprite().getIconHeight() - CHUNK;
                graphicElement.setPosition(
                    (int) colliderPosition.getX() - loadingStartingPoint * CHUNK,
                    (int) colliderPosition.getY() + heightDifference
                );
                graphicElement.add();
            }
        }

        LoaderCollider loader = new LoaderCollider(new Rectangle(0, 0, CHUNK, 2 * windowHeight));
        loader.activate();

        for (int i =  0; i <= lastChunkInScreen; i++) {
            loader.translate(CHUNK, 0);
            collisionsEngine.update();
        }

        GraphicUnloaderCollider unloader = new GraphicUnloaderCollider(new Rectangle((loadingStartingPoint - 2) * CHUNK, 0, CHUNK, 2 * windowHeight));
        unloader.activate();

        DeleterCollider deleter = new DeleterCollider(new Rectangle((loadingStartingPoint - 6) * CHUNK, 0, CHUNK, 2 * windowHeight));
        deleter.activate();
        
        ScreenBorderCollider leftBorder = new ScreenBorderCollider(
            new Rectangle((loadingStartingPoint - 1) * CHUNK, 0, CHUNK, windowHeight),
            Direction.LEFT
        );
        leftBorder.activate();

        ScreenBorderCollider rightBorder = new ScreenBorderCollider(
            new Rectangle(lastChunkInScreen * CHUNK - CHUNK / 2, 0, CHUNK, 2 * windowHeight),
            Direction.RIGHT
        );
        rightBorder.activate();

        int halfScreen = (int) Math.ceil(GraphicEngine.instance().getPanelSize().getWidth() / 2);
        new ScreenDisplacementCollider(
            new Rectangle(halfScreen + loadingStartingPoint * CHUNK, 0, CHUNK, 2 * windowHeight),
            leftBorder,
            rightBorder,
            loader,
            unloader,
            deleter
        ).activate();

        LevelEndCollider le = new LevelEndCollider(new Rectangle(row * CHUNK - halfScreen, 0,  CHUNK, 2 * windowHeight));
        le.activate();
    }
    
    public void loadEntities(BufferedReader br) throws IOException {
        chunk = br.readLine();
        BlockLoader blockLoader = new BlockLoader();
        row = 0;
        while (chunk != null) {
            for (column = 0; column < chunk.length(); column++) {
                char character = chunk.charAt(column);
                if (character != ' ') {
                    loaders.getOrDefault(character, blockLoader).load(this);
                }
            }
            row++;
            chunk = br.readLine();
        }

    }

    public void setColumn(int i) {
        column = i;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int i) {
        row = i;
    }

    public int getRow() {
        return row;
    }

    public void setChunk(String s) {
        chunk = s;
    }

    public String getChunk() {
        return chunk;
    }
}

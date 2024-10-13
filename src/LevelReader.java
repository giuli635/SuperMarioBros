import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

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

        for (int i = 0; i < lastChunkInScreen; i++) {
            for (Collider collider : collisionsEngine.getChunk(i)) {
                GraphicElement graphicElement = collider.getEntity().getGraphicElement();
                Point colliderPosition = collider.getPosition();
                int yPosition = (int) (windowHeight - colliderPosition.getY());
                graphicElement.setPosition((int) colliderPosition.getX(), yPosition);
                graphicEngine.addGraphicElement(graphicElement);
            }
        }
    }
    
    public void loadEntities(BufferedReader br) throws IOException {
        String chunk;
        chunk = br.readLine();
        int i = 0;
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

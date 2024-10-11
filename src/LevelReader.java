import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LevelReader  {
    protected static LevelReader uniqueInstance;
    protected Map<Character,Entity> prototypes;
    protected GameEntity generalPrototype;
    protected Mario marioPrototype;

    public LevelReader() {
        marioPrototype = new Mario();
        generalPrototype = new GameEntity();
    }

    protected static LevelReader instance(){
        return uniqueInstance;
    }
    
    public Level createLevel(String file) {
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String chunk;
            chunk = br.readLine();
            int ind = 0;
            while (chunk != null) {
                for (int i = 0; i < chunk.length(); i++) {
                    char item = chunk.charAt(i);
                    if (item == 'm') {
                        Entity newentity = marioPrototype.clone();
                    }
                }
                ind++;
                chunk = br.readLine();
            }
            br.close();
        } catch(IOException e) {
            e.getStackTrace();
        }

        return null;
    }
}
package graphics;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

public class SpriteFactory {
    protected static SpriteFactory uniqueInstance;
    protected static String SPRITES_DIR = "sprites";
    protected Map<String, Map<String, ImageIcon>> sprites;

    private SpriteFactory() {
        sprites = new HashMap<>();
    }

    public static SpriteFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SpriteFactory();
        }
        return uniqueInstance;
    }

    public Map<String, ImageIcon> getSprites(String entity) {
        return loadSprites(entity);
    }
    
    private Map<String, ImageIcon> loadSprites(String entity) {
        Map<String, ImageIcon> entitySprites = new ConcurrentHashMap<>();
        Path spritePath = Paths.get(SPRITES_DIR, entity, "mode1");
        File spriteDir = spritePath.toFile();
        
        File[] spriteFiles = spriteDir.listFiles();

        for (File sprite : spriteFiles) {
            String spriteName = sprite.getName();
            ImageIcon spriteImage = loadImage(sprite);
            entitySprites.put(spriteName, spriteImage);
        }

        return entitySprites;
    }
    
    private ImageIcon loadImage(File file) {
        return new ImageIcon(file.getAbsolutePath());
    }
    
    public void clearCache() {
        sprites.clear();
    }
    
    public boolean hasSpritesLoaded(String entity) {
        return sprites.containsKey(entity) && !sprites.get(entity).isEmpty();
    }
}

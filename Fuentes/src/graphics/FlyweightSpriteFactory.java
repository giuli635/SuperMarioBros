package graphics;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

public class FlyweightSpriteFactory {
    protected static FlyweightSpriteFactory uniqueInstance;
    protected static String SPRITES_DIR = "sprites";
    protected Map<String, Map<String, ImageIcon>> sprites;

    private FlyweightSpriteFactory() {
        sprites = new HashMap<>();
    }

    public static FlyweightSpriteFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FlyweightSpriteFactory();
        }
        return uniqueInstance;
    }

    public Map<String, ImageIcon> getSprites(String entity, String mode) {
        return loadSprites(entity, mode);
    }
    
    private Map<String, ImageIcon> loadSprites(String entity, String mode) {
        Map<String, ImageIcon> entitySprites = new ConcurrentHashMap<>();
        Path spritePath = Paths.get(SPRITES_DIR, entity, mode);
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

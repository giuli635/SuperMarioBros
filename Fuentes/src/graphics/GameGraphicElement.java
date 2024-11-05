package graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entities.Entity;
import game.GraphicEngine;

public class GameGraphicElement extends BaseTranslatableGraphicElement {
    protected Entity entity;

    protected ImageIcon sprite;
    protected String currentSprite;
    protected String lastNotNullSprite;
    protected Map<Color, Color> colorRemap;
    protected boolean flipped;
    protected boolean iconUpdated;

    protected Map<String, ImageIcon> sprites;
    protected String folder;

    public GameGraphicElement(Entity e, String folderPath) {
        super();
        folder = folderPath;
        entity = e;
        sprite = null;
        flipped = false;
        reload();
    }
    
    public Entity getEntity() {
        return entity;
    }

    public String getSpriteName() {
        return currentSprite;
    }

    public ImageIcon getSprite() {
        return sprite;
    }

    public String lastNotNullSpriteName() {
        return lastNotNullSprite;
    }

    public void setSprite(String s) {
        if (s != currentSprite) {
            forcefullyUpdateSprite(s);
        }
    }

    protected void forcefullyUpdateSprite(String s) {
        GraphicEngine.instance().addToRedraw(this);
        iconUpdated = true;

        if (s != null) {
            lastNotNullSprite = s;
            
            sprite = sprites.get(s + ".png");

            if (flipped) {
                sprite = new ImageIcon(flipImage(iconToBufferedImage(sprite)));
            }

            if (colorRemap != null) {
                sprite = remapSpriteColor(colorRemap, sprite);
            }

            bounds.setSize(sprite.getIconWidth(), sprite.getIconHeight());
        } else {
            sprite = null;
            bounds.setSize(0, 0);
        }

        currentSprite = s;
    }

    @Override
    public JLabel getComponent() {
        return label;
    }

    @Override
    public void reload() {
        loadSprites();
    }

    @Override
    public void redraw() {
        super.redraw();

        if (iconUpdated) {
            label.setIcon(sprite);
            iconUpdated = false;
        }
    }

    protected void loadSprites() {
        sprites = SpriteFactory.instance().getSprites(folder, GraphicEngine.instance().getMode());
        forcefullyUpdateSprite(currentSprite);
    }

    public void setFolder(String f) {
        folder = f;
        reload();
    }

    public String getFolder() {
        return folder;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public Map<Color, Color> getColorRemap() {
        return colorRemap;
    }

    public void setColorRemap(Map<Color, Color> colorRemap) {
        GraphicEngine.instance().addToRedraw(this);
        iconUpdated = true;

        this.colorRemap = colorRemap;
        sprite = remapSpriteColor(colorRemap, sprite);
    }

    public void removeColorRemap() {
        colorRemap = null;
        forcefullyUpdateSprite(currentSprite);
    }

    public void flipSprite() {
        GraphicEngine.instance().addToRedraw(this);
        iconUpdated = true;

        flipped = !flipped;
        
        if (sprite != null) {
            sprite = new ImageIcon(flipImage(iconToBufferedImage(sprite)));
        }
    }

    public static BufferedImage flipImage(BufferedImage image) {
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);
        transform.translate(-image.getWidth(), 0);
        BufferedImage flippedImage = new BufferedImage(
            image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return flippedImage;
    }

    public static ImageIcon remapSpriteColor(Map<Color, Color> colorRemap, ImageIcon sprite) {
        BufferedImage bufferedImage = iconToBufferedImage(sprite);
        for (int x = 0; x < bufferedImage.getWidth(); x++) {          
            for (int y = 0; y < bufferedImage.getHeight(); y++) {   
                Color newColor = colorRemap.get(new Color(bufferedImage.getRGB(x, y), true));
                if (newColor != null) {
                    bufferedImage.setRGB(x, y, newColor.getRGB());
                }
            }
        }

        return new ImageIcon(bufferedImage);
    }

    public static BufferedImage iconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(
            icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(icon.getImage(), 0, 0, null);
        g.dispose();
        return bufferedImage;
    }
}

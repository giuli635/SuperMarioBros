package graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entities.Entity;
import game.GraphicEngine;

public class GameGraphicElement implements GraphicElement {
    protected Entity entity;
    protected JLabel label;
    protected ImageIcon sprite;
    protected Rectangle bounds;
    protected boolean toUpdate;
    protected Map<String, ImageIcon> sprites;
    protected String folder;

    public GameGraphicElement(Entity e, String folderPath, String mode) {
        folder = folderPath;
        entity = e;
        sprite = null;
        label = new JLabel();
        bounds = label.getBounds();
        loadSprites(mode);
    }
    
    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void translate(int dx, int dy) {
        toUpdate = true;
        bounds.translate(dx, dy);
    }

    @Override
    public Point getPosition() {
        return bounds.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        toUpdate = true;
        bounds.setLocation(x, y);
    }

    @Override
    public ImageIcon getCurrentSprite() {
        return sprite;
    }

    @Override
    public void setSprite(String s) {
        sprite = sprites.get(s + ".png");
        toUpdate = true;
        bounds.setSize(sprite.getIconWidth(), sprite.getIconHeight());
    }

    public void flipSprite(String s) {
        ImageIcon originalIcon = sprites.get(s + ".png");

        BufferedImage originalImage = iconToBufferedImage(originalIcon);

        BufferedImage flippedHorizontally = flipImage(originalImage);

        sprite = new ImageIcon(flippedHorizontally);
        toUpdate = true;
        bounds.setSize(sprite.getIconWidth(), sprite.getIconHeight());

    }

    public void draw() {
        if (toUpdate) {
            Rectangle boundsToDraw = new Rectangle(bounds);
            boundsToDraw.setLocation(
                (int) bounds.getX(),
                (int) (GraphicEngine.instance().getPanelSize().getHeight() - bounds.getY())
            );
            label.setBounds(boundsToDraw);
            label.setIcon(sprite);
            toUpdate = false;
        }
    }

    @Override
    public JLabel getLabel() {
        return label;
    }

    @Override
    public void loadSprites(String mode) {
        sprites = SpriteFactory.instance().getSprites(folder);
    }

    @Override
    public void setFolder(String f) {
        folder = f;
    }

    @Override
    public String getFolder() {
        return folder;
    }

    public static BufferedImage iconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(icon.getImage(), 0, 0, null);
        g.dispose();
        return bufferedImage;
    }

    public static BufferedImage flipImage(BufferedImage image) {
        AffineTransform transform = new AffineTransform();

        
        transform.scale(-1, 1);
        transform.translate(-image.getWidth(), 0);


        // Create a new BufferedImage to draw the flipped image
        BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return flippedImage;
    }
}

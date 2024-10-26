package graphics;
import java.awt.Graphics2D;
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
    protected String currentSprite;
    protected Rectangle bounds;
    protected boolean toUpdate;
    protected boolean flipped;

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    protected Map<String, ImageIcon> sprites;
    protected String folder;

    public GameGraphicElement(Entity e, String folderPath) {
        folder = folderPath;
        entity = e;
        sprite = null;
        label = new JLabel();
        flipped = false;
        bounds = label.getBounds();
        loadSprites();
    }
    
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

    public ImageIcon getCurrentSprite() {
        return sprite;
    }

    public void setSprite(String s) {
        if(sprite != null){
            int preHeight = sprite.getIconHeight();
            sprite = sprites.get(s + ".png");
            toUpdate = true;
            flipped = false;
            bounds.setSize(sprite.getIconWidth(), sprite.getIconHeight());
            int posHeight = sprite.getIconHeight();
            int diff = preHeight - posHeight;
            int sign = (int) Math.signum(diff);
            translate(0,- sign * Math.abs(diff));
        } else {
            sprite = sprites.get(s + ".png");
            toUpdate = true;
            flipped = false;
            bounds.setSize(sprite.getIconWidth(), sprite.getIconHeight());
        }
    }

    public void flipSprite() {
        toUpdate = true;
        flipped = true;
        sprite = new ImageIcon(flipImage(iconToBufferedImage(sprite)));
        bounds.setSize(sprite.getIconWidth(), sprite.getIconHeight());
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
        BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return flippedImage;
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
    public JLabel getComponent() {
        return label;
    }

    public void loadSprites() {
        sprites = SpriteFactory.instance().getSprites(folder, GraphicEngine.instance().getMode());
    }

    public void setFolder(String f) {
        folder = f;
        loadSprites();
        setSprite(currentSprite);
    }

    public String getFolder() {
        return folder;
    }
}

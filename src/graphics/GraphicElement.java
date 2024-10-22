package graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entities.Entity;

public interface GraphicElement {
    public Entity getEntity();
    public void translate(int dx, int dy);
    public Point getPosition();
    public void setPosition(int x, int y);
    public ImageIcon getCurrentSprite();
    public void setSprite(String s);
    public JLabel getLabel();
    public void draw();
    public void loadSprites(String mode);
    public void setFolder(String folder);
    public String getFolder();
}

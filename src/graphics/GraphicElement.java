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
    public void flipSprite();
    public JLabel getLabel();
    public JLabel[] getLabels();
    public void draw();
    public void loadSprites();
    public void setFolder(String folder);
    public String getFolder();
    public boolean isFlipped();
    public void setFlipped(boolean f);
}

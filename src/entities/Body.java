package entities;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import colliders.Collider;
import graphics.GameGraphicElement;

public abstract class Body implements Entity {
    public void setSpritesFolder(String folder) {
        GameGraphicElement graphicElement = getGraphicElement();
        String previousSprite = graphicElement.lastNotNullSpriteName();
        graphicElement.setSprite(previousSprite);
        ImageIcon previousSpriteIcon = graphicElement.getSprite();
        graphicElement.setFolder(folder);
        graphicElement.setSprite(previousSprite);
        ImageIcon newSprite = graphicElement.getSprite();
        adjustGraphicElementOnChange(newSprite, previousSpriteIcon);
    }

    public void setSprite(String s) {
        GameGraphicElement graphicElement = getGraphicElement();
        ImageIcon previousSprite = graphicElement.getSprite();
        graphicElement.setSprite(s);
        ImageIcon sprite = graphicElement.getSprite();
        adjustGraphicElementOnChange(sprite, previousSprite);
    }

    public void adjustGraphicElementOnChange(ImageIcon newSprite, ImageIcon previousSprite) {
        if (previousSprite != null) {
            int preHeight = previousSprite.getIconHeight();
            int posHeight = newSprite.getIconHeight();
            int diff = preHeight - posHeight;
            getGraphicElement().translate(0, -diff);
        }

        if (newSprite != null) {
            adjustColliderToGraphicElement();
        }
    }

    public void adjustColliderToGraphicElement() {
        ImageIcon sprite = getGraphicElement().getSprite();
        getCollider().setSize(sprite.getIconWidth(), sprite.getIconHeight());
    }

    public void translate(int dx, int dy) {
        getGraphicElement().translate(dx, dy);
        getCollider().translate(dx, dy);
    }

    public int displaceHorizontally(Collider c) {
        Collider collider = getCollider();
        Rectangle collision = collider.getBounds().intersection(c.getBounds());

        int displacement = collider.displaceX(collision, 0);
        getGraphicElement().translate(displacement, 0);
        return displacement;
    }

    public int displaceVertically(Collider c) {
        Collider collider = getCollider();
        Rectangle collision = collider.getBounds().intersection(c.getBounds());

        int displacement = collider.displaceY(collision, 0);
        getGraphicElement().translate(0, displacement);
        return displacement;
    }
}

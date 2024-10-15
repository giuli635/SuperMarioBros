package entities;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import colliders.Collider;
import colliders.GameCollider;
import game.Game;
import graphics.GraphicElement;
import graphics.GameGraphicElement;

public class GameEntity implements Entity {
    protected Collider collider;
    protected GraphicElement graphicElement;
    protected boolean loaded;

    public GameEntity() {
        loaded = false;
        collider = new GameCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, new ImageIcon("sprites/block.png"));
    }

    public Entity clone() {
        return new GameEntity();
    }

    public void load() {
        Game.instance().registerToUpdate(this);
    }

    public void unload() {
        Game.instance().unregisterToUpdate(this);
    }

    public boolean loaded() {
        return loaded;
    }
    
    public Collider getCollider() {
        return collider;
    }

    public GraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}

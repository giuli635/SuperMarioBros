import java.awt.Rectangle;

class GameEntity implements Entity {
    protected Collider collider;
    protected GraphicElement graphicElement;
    protected boolean loaded;

    public GameEntity() {
        loaded = false;
        collider = new GameCollider(this, new Rectangle(20, 20));
        graphicElement = new GameGraphicElement(this, null);
    }

    public GameEntity clone() {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}

public interface Entity {
    public void update();
    public boolean loaded();
    public void load();
    public void unload();
    public GraphicElement getGraphicElement();
    public Collider getCollider();
    public Entity clone();
}

public interface GraphicElement {
    public void draw();
    public Position getPosition();
    public void setPosition(Position p);
    public Sprite getSprite();
    public void setSprite(Sprite s);
    public void translate(int dx, int dy);
}

public class Mario implements Entity {
    private GraphicElememt graphicElememt;
    private Collider collider;
    private Position position;
    private int speedX; 
    private int lifes;


    public Mario(GraphicElement gh, Collider c, Position p) {
        graphicElement = gh;
        collider = c;
        position = p;
    
    }

    public update() {
        boolean keyPressed;
        switch (keyPressed) {
            case Game.getKeyPressed(KeyEvent.VK_A):
                graphicElement.translate(10, 0);
                graphicElement.setSprite();
                collider.translate(10);
            break;
    
            case Game.getKeyPressed(KeyEvent.VK_D):
                graphicElement.translate(10, 0);
                graphicElement.setSprite();
                collider.translate(10);
            break;
    
            case Game.getKeyPressed(KeyEvent.VK_W):
                graphicElement.translate(0, 10);
                graphicElement.setSprite();
                collider.translate(x,y);
            break;
    
            case Game.getKeyPressed(KeyEvent.VK_SPACE):
                graphicElement.setSprite();
            break;
        }
        


    }


}

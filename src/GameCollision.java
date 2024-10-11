public class GameCollision implements Collision {
    @Override
    public void collide(GameCollider c, Direction d) {
        c.handleCollision(this, d);
    }
}

public interface Collision {
    public void collide(MarioCollider c, Direction d);
    public void collide(EnemyCollider c, Direction d);
    public void collide(PlatformCollider c, Direction d);
    public void collide(CoinCollider c, Direction d);
    public void collide(PowerUpCollider c, Direction d);
    public void collide(FireBallCollider c, Direction d);
}

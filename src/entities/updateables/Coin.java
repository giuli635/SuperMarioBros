package entities.updateables;

import java.awt.Rectangle;
import java.util.List;

import colliders.CoinCollider;
import graphics.GameGraphicElement;

public class Coin extends UpdateableBody {
    protected static String SPRITES_FOLDER = "coin";
    public final static List<String> ANIMATED_SPRITES = List.of(
        "coin2", "coin3"
    );
    public final static int FRAMES_PER_SPRITE = 10;
    public final static String SOUND = "coin.wav";
    public final static int POINTS = 5;

    protected CoinCollider collider;
    protected GameGraphicElement graphicElement;
    protected MovementAnimator animator;
    
    public Coin() {
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        collider = new CoinCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void update() {
        animator.animate();
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public CoinCollider getCollider() {
        return collider;
    }
}

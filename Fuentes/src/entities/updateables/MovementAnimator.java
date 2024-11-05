package entities.updateables;

import java.util.List;

import entities.Body;

public class MovementAnimator implements Animator {
    protected Body entity;
    protected CyclicIterator<String> spriteIterator;
    protected String currentSprite;
    protected boolean wasFlipped;

    public MovementAnimator(List<String> sprites, int framesPerSprite, Body e) {
        spriteIterator = new CyclicIterator<>(sprites, framesPerSprite);
        entity = e;
    }

    public void animate() {
        String nextSprite = spriteIterator.next();
        if (nextSprite != currentSprite) {
            entity.setSprite(nextSprite);
            currentSprite = nextSprite;
        }
    }
}

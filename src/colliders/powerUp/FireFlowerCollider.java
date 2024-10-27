package colliders.powerUp;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.UpdateableEntityCollider;
import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.SuperMushroomCollision;
import entities.powerUp.FireFlower;
import entities.powerUp.SuperMushroom;
import entities.UpdatableEntity;
import entities.mario.Mario;
import entities.mario.SuperMario;
import game.CollisionsEngine;
import game.GraphicEngine;

public class FireFlowerCollider extends BaseCollider implements UpdateableEntityCollider {
    protected FireFlower f;

    public FireFlowerCollider(FireFlower fireFlower, Rectangle b) {
        super(b);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendCollision'");
    }

    @Override
    public Collision getCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollision'");
    }

    @Override
    public UpdatableEntity getEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEntity'");
    }

}

package entities;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import colliders.BlockCollider;
import graphics.GameGraphicElement;

public class Block extends BaseEntity {
    public Block() {
        collider = new BlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, new ImageIcon("sprites/block.png"));
    }
}

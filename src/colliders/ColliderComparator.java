package colliders;

import java.awt.Point;
import java.util.Comparator;

public class ColliderComparator implements Comparator<Collider> {

    @Override
    public int compare(Collider arg0, Collider arg1) {
        Point colliderPostion0 = arg0.getPosition();
        Point colliderPostion1 = arg1.getPosition();

        if (colliderPostion0.getX() == colliderPostion1.getX()) {
            return 0;
        } else if (colliderPostion0.getX() > colliderPostion1.getX()) {
            return 1;
        } else {
            return -1;
        }
    }
}

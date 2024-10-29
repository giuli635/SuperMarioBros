package entities.updateables.mario.actions;

import java.util.Comparator;

public class ActionComparator implements Comparator<MarioAction> {
    @Override
    public int compare(MarioAction arg0, MarioAction arg1) {
        return Integer.compare(arg1.getPriority(), arg0.getPriority());
    }
}

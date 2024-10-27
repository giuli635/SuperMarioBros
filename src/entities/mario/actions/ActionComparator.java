package entities.mario.actions;

import java.util.Comparator;

public class ActionComparator implements Comparator<MarioAction> {
    @Override
    public int compare(MarioAction arg0, MarioAction arg1) {
        return Integer.compare(arg0.getPriority(), arg1.getPriority());
    }
}

package utils;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Prioritizable> {
    @Override
    public int compare(Prioritizable arg0, Prioritizable arg1) {
        return Integer.compare(arg1.getPriority(), arg0.getPriority());
    }
}

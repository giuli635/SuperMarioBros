package entities.updateables;

import java.util.List;

import game.Game;

public class CyclicIterator<E> {
    protected List<E> list;
    protected int delay;
    protected int currentIndex;

    public CyclicIterator(List<E> l, int delay) {
        list = l;
        this.delay = delay;
        currentIndex = 0;
    }

    public E next() {
        currentIndex += (Game.instance().getFrames() % delay) == 0 ? 1 : 0;
        currentIndex %= list.size();
        return list.get(currentIndex);
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}

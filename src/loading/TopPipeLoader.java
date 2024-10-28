package loading;

import entities.Pipe;
import game.LevelReader;

public class TopPipeLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Pipe pipe = new Pipe();
        positionCollider(pipe, lr.getRow(), lr.getColumn());
    }
}

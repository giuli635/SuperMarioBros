package loading;


import entities.Pipe;
import game.LevelReader;

public class BasePipeLoader extends BaseLoader{
    public void load(LevelReader lr) {
        Pipe pipe = new Pipe(1);
        positionCollider(pipe, lr.getRow(), lr.getColumn());
    }
}

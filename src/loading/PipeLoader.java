package loading;


import entities.solids.Pipe;
import game.LevelReader;

public class PipeLoader extends BaseLoader{
    public void load(LevelReader lr) {
        Pipe pipe = new Pipe(lr.getChunk().charAt(lr.getColumn()));
        positionCollider(pipe, lr.getRow(), lr.getColumn());
    }
}

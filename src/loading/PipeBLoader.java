package loading;


import entities.Pipe;

public class PipeBLoader implements EntityLoader{
    public Pipe load() {
        return new Pipe(1);
    }
}

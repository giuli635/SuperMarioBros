package loading;

import entities.Pipe;

public class PipeLoader implements EntityLoader{
    @Override
    public Pipe load() {
        return new Pipe();
    }
}

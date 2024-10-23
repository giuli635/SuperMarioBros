package loading;

import entities.QuestionBlock;

public class QuestionBlockLoader implements EntityLoader{
    @Override
    public QuestionBlock load() {
        return new QuestionBlock();
    }
}

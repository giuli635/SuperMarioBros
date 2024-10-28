package loading;

import entities.QuestionBlock;
import game.LevelReader;

public class QuestionBlockLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        lr.setColumn(lr.getColumn() + 1);
        char character = lr.getChunk().charAt(lr.getColumn());
        QuestionBlock questionBlock = new QuestionBlock(character);
        positionCollider(questionBlock, lr.getRow(), lr.getColumn() - 1);
    }
}

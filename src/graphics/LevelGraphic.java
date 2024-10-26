package graphics;

import game.GraphicEngine;
import game.LevelStats;
import game.LevelStatsObserver;

public class LevelGraphic implements LevelStatsObserver {
    private TimerGraphicElement timerGraphic;
    private MarioGraphicLives livesGraphic;
    private ScoreGraphicElement scoreGraphic;
    private LevelStats levelStats;
    
    public LevelGraphic(LevelStats stats) {
        this.levelStats = stats;
        this.levelStats.addObserver(this);
        initializeGraphics();
    }
    
    private void initializeGraphics() {
        timerGraphic = new TimerGraphicElement(levelStats.getLevelTimer());
        GraphicEngine.instance().addGraphicElement(timerGraphic);
        
        livesGraphic = new MarioGraphicLives(levelStats.getLives());
        GraphicEngine.instance().addGraphicElement(livesGraphic);
        
        scoreGraphic = new ScoreGraphicElement(levelStats.getScore());
        GraphicEngine.instance().addGraphicElement(scoreGraphic);
    }
    
    @Override
    public void onStatsChanged() {
        livesGraphic.updateLives(levelStats.getLives());
        scoreGraphic.updateScore(levelStats.getScore());
    }
}
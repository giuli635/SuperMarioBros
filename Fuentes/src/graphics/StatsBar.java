package graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.SingletonGraphicEngine;
import game.LanguageConfiguration;
import game.Stats;
import game.StatsPanel;
import game.ObserverLevelStats;

import java.awt.Font;
import java.awt.Component;

public class StatsBar extends BaseGraphicElement implements ObserverLevelStats {
    protected Font font;

    protected StatsPanel timePanel;
    protected StatsPanel livesPanel;
    protected StatsPanel levelPanel;
    protected StatsPanel scorePanel;
    protected JPanel mainPanel;
    protected Stats levelStats;

    public StatsBar(Stats l) {
        levelStats = l;
        levelStats.addObserver(this);

        mainPanel = new JPanel();

        font = SingletonGraphicEngine.instance().getFont().deriveFont(38f);

        scorePanel = createPanel(font);
        timePanel = createPanel(font);
        levelPanel = createPanel(font);
        livesPanel = createPanel(font);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
                mainPanel.setBounds(0, 0, (int) SingletonGraphicEngine.instance().getPanelSize().getWidth(), 100);
                mainPanel.setOpaque(false);
                mainPanel.add(Box.createHorizontalGlue());
                addElement(scorePanel);
                addElement(levelPanel);
                addElement(timePanel);
                addElement(livesPanel);
                redraw();
            }
        });
    }

    public StatsPanel createPanel(Font font) {
        StatsPanel panel = new StatsPanel(font);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.setOpaque(false);

        return panel;
    }

    public void addElement(StatsPanel element) {
        mainPanel.add(element);
        mainPanel.add(Box.createHorizontalGlue());
    }

    public void redraw() {
        timePanel.setStat(levelStats.getRemainingTime());
        livesPanel.setStat(levelStats.getLives());
        levelPanel.setStat(levelStats.getLevelNumber());
        scorePanel.setStat(levelStats.getScore());
    }

    @Override
    public JComponent getComponent() {
        return mainPanel;
    }

    public Font getFont() {
        return font;
    }

    @Override
    public void onStatsChanged() {
        SingletonGraphicEngine.instance().addToRedraw(this);
    }

    @Override
    public void reload() {
        SingletonGraphicEngine.instance().addToRedraw(this);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                timePanel.setText(LanguageConfiguration.instance().get("time"));
                livesPanel.setText(LanguageConfiguration.instance().get("lives"));
                levelPanel.setText(LanguageConfiguration.instance().get("level"));
                scorePanel.setText(LanguageConfiguration.instance().get("score"));
            }
        });
    }
}

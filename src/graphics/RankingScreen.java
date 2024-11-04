package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.RankingManager;
import game.RankingManager.ScoreEntry;

public class RankingScreen extends ScreenOverlay {
    protected RankingManager rankingManager;

    public RankingScreen(RankingManager ranking) {
        super("highScore");
        rankingManager = ranking;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initOverlay();
            }
        });
    }

    @Override
    protected void initOverlay() {
        int row = 1;
        for (ScoreEntry entry : rankingManager.getHighScores()) {
            JLabel scoreLabel = new JLabel(row + ". " + entry.getName() + " - " + entry.getScore());
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(font.deriveFont(30f));

            panel.add(scoreLabel);
            row++;
        }

        panel.add(Box.createVerticalGlue());

        // TODO: FRANNNNNNNNNNNNNNNNNNNN
        JButton exit = new JButton("< Return");
        exit.setFont(font.deriveFont(30f));
        exit.setForeground(Color.WHITE);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setFocusable(false);
        exit.setBorderPainted(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                remove();
            }
        });
        panel.add(exit);

        panel.add(Box.createVerticalGlue());

        super.initOverlay();
    }
}

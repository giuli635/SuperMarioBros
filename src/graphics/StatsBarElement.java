package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsBarElement {
    JPanel panel;
    JLabel label;
    JLabel textLabel;

    public StatsBarElement(StatsBar bar, String text) {
        textLabel = new JLabel();
        label = new JLabel();
        panel = new JPanel();

        Font customFont = bar.getFont();
        textLabel.setFont(customFont);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setForeground(Color.WHITE);
        textLabel.setText(text);
        // label.setText("" + levelTimer.getRemainingTime());
        label.setFont(customFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);
        
        panel.add(textLabel);
        panel.add(label);
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.setOpaque(false);
    }

    public JPanel getPanel() {
        return panel;
    }
}

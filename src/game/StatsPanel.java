package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel{
    protected JLabel text;
    protected JLabel stat;

    public StatsPanel(Font f){
        text = new JLabel();
        stat = new JLabel();
        text.setFont(f);
        stat.setFont(f);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setForeground(Color.WHITE);
        stat.setAlignmentX(Component.CENTER_ALIGNMENT);
        stat.setForeground(Color.WHITE);
        add(text);
        add(stat);
    }

    public String getText() {
        return text.getText();
    }

    public void setText(String s) {
        text.setText(s);
    }

    public void setStat(int t) {
        stat.setText("" + t);
    }
}

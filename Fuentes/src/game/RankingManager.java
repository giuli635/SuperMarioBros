package game;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class RankingManager {
    private static final String FILE_PATH = "ranking.txt";
    private List<ScoreEntry> highScores;

    public RankingManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }

    public void addScore(String playerName, int score) {
        highScores.add(new ScoreEntry(playerName, score));
        highScores.sort((a, b) -> Integer.compare(b.score, a.score));
        if (highScores.size() > 5) {
            highScores = highScores.subList(0, 5); 
        }
        saveHighScores();
    }

    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    highScores.add(new ScoreEntry(name, score));
                }
            }
            highScores.sort((a, b) -> Integer.compare(b.score, a.score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ScoreEntry entry : highScores) {
                writer.write(entry.playerName + ":" + entry.score);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAndUpdateRanking(int score) {
        if ((highScores.size() < 5) || (score > highScores.get(4).getScore())) {
            String playerName = JOptionPane.showInputDialog(LanguageConfiguration.instance().get("enterName"));

            GraphicEngine.instance().focusFrame();

            if (playerName != null && !playerName.trim().isEmpty()) {
                addScore(playerName, score);
                return true;
            }
            else{
                addScore("ANONIMO", score);
                return true;
            }
        }
        return false;
    }

    public List<ScoreEntry> getHighScores() {
        return highScores;
    }

    public static class ScoreEntry {
        String playerName;
        int score;  

        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getName(){
            return playerName;
        }

        public int getScore(){
            return score;
        }

        public String toString() {
            return playerName + ": " + score;
        }
    }
}

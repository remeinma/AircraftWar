package edu.hitsz.application;

import edu.hitsz.application.Game.Game;
import edu.hitsz.score.ScoreRanking;
import edu.hitsz.score.ScoreRankingDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static edu.hitsz.application.Main.game;

public class InputUsername {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextArea description;
    private JTextField input;
    private JButton confirm;
    private JButton cancel;
    private JFrame self;
    private ScoreTable scoreTable;

    public void setScoreTable(ScoreTable scoreTable) {
        this.scoreTable = scoreTable;
    }
    private final ScoreRankingDaoImpl scoreRankingDao = new ScoreRankingDaoImpl();
    private List<ScoreRanking> scoreRankingList;

    public void setSelf(JFrame self) {
        this.self = self;
    }
    public boolean saveScore = false;

    public void Print(int score,String name, boolean flag) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String time = format.format(new Date());
        scoreRankingDao.readFile();
        scoreRankingDao.clearFile();
        if(flag){
            scoreRankingDao.doAdd(new ScoreRanking(name, score, time));
        }
        scoreRankingList = scoreRankingDao.getAllScores();
        scoreRankingDao.writeFile(scoreRankingList);
    }
    public InputUsername(int score, String degree) {
        description.setText("游戏结束，你的得分为" + score + "，\n请输入名字记录得分：");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScore = true;
                try {
                    Print(Game.getScore(),input.getText(), saveScore);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                scoreTable.refresh();
                self.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScore = false;
                self.dispose();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

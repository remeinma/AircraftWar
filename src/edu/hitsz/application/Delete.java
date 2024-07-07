package edu.hitsz.application;

import edu.hitsz.score.ScoreRanking;
import edu.hitsz.score.ScoreRankingDao;
import edu.hitsz.score.ScoreRankingDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Delete {
    private JPanel panel1;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextField description;
    private JButton confirm;
    private JButton cancel;
    private JFrame self;
    private final ScoreRankingDaoImpl scoreRankingDao = new ScoreRankingDaoImpl();
    private ScoreTable scoreTable;
    public void setScoreTable(ScoreTable scoreTable) { this.scoreTable = scoreTable; }
    public void setSelf(JFrame self) { this.self = self; }

    public Delete(String time) {
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreRankingDao.readFile();
                scoreRankingDao.doDelete(time);
                scoreRankingDao.clearFile();
                List<ScoreRanking> scoreRankingList = scoreRankingDao.getAllScores();
                scoreRankingDao.writeFile(scoreRankingList);
                scoreTable.refresh();
                self.dispose();
            }
        });


        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.dispose();
            }
        });
        description.setText("是否确认删除该条选中记录？");
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}

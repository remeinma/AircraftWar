package edu.hitsz.application;

import edu.hitsz.score.ScoreRanking;
import edu.hitsz.score.ScoreRankingDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static edu.hitsz.application.Main.game;

public class ScoreTable {
    private JPanel MainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JScrollPane tableScrollPanel;
    private JTable scoreTable;
    private JButton deleteButton;
    private JLabel header;
    private JTextField degree;
    private final ScoreRankingDaoImpl scoreRankingDao = new ScoreRankingDaoImpl();
    private List<ScoreRanking> scoreRankingList;



    public ScoreTable() throws IOException {
        scoreRankingDao.readFile();
        scoreRankingList = scoreRankingDao.getAllScores();
        int num = scoreRankingList.size();
        String[] columnName={"名次","用户","分数","记录时间"};
        String[][] tableData = new String[num][4];

        for(int i = 0; i < num; i ++) {
            ScoreRanking scoreRanking = scoreRankingList.get(i);
            tableData[i][0] = (i+1) + "";
            tableData[i][1] = scoreRanking.getName();
            tableData[i][2] = scoreRanking.getScore() + "";
            tableData[i][3] = scoreRanking.getTime();
        }
        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
//从表格模型那里获取数据
        scoreTable.setModel(model);
        tableScrollPanel.setViewportView(scoreTable);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoreTable.getSelectedRow();
                if(row!=-1){
                    String time = scoreRankingList.get(row).getTime();
                    //弹窗
                    Delete delete = new Delete(time);
                    JFrame deleteFrame = new JFrame("删除");
                    delete.setSelf(deleteFrame);
                    delete.setScoreTable(ScoreTable.this);
                    deleteFrame.setLocationRelativeTo(null);
                    deleteFrame.setContentPane(delete.getMainPanel());
                    deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    deleteFrame.setUndecorated(true);
                    deleteFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
                    deleteFrame.pack();
                    deleteFrame.setResizable(false);
                    deleteFrame.setVisible(true);



//                    model.removeRow(row);
//                    scoreRankingDao.readFile();
//                    scoreRankingDao.doDelete(tableData[row-1][3]);
//                    scoreRankingDao.clearFile();
//                    scoreRankingList = scoreRankingDao.getAllScores();
//                    scoreRankingDao.writeFile(scoreRankingList);
                }
            }
        });

        String degreeText;
        switch (Main.degree) {
            case "easy":
                degreeText = "难度：简单";
                break;
            case "normal":
                degreeText = "难度：普通";
                break;
            case "hard":
                degreeText = "难度：困难";
                break;
            default:
                degreeText = "难度：";
        }
        degree.setText(degreeText);
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void refresh() {
        scoreRankingDao.readFile();
        scoreRankingList = scoreRankingDao.getAllScores();
        int num = scoreRankingList.size();
        String[] columnName={"名次","用户","分数","记录时间"};
        String[][] tableData = new String[num][4];

        for(int i = 0; i < num; i ++) {
            ScoreRanking scoreRanking = scoreRankingList.get(i);
            tableData[i][0] = (i+1) + "";
            tableData[i][1] = scoreRanking.getName();
            tableData[i][2] = scoreRanking.getScore() + "";
            tableData[i][3] = scoreRanking.getTime();
        }
        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
//从表格模型那里获取数据
        scoreTable.setModel(model);
        tableScrollPanel.setViewportView(scoreTable);
    }
}

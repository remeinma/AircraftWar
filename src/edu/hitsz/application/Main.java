package edu.hitsz.application;

import edu.hitsz.application.Game.EasyGame;
import edu.hitsz.application.Game.Game;
import edu.hitsz.application.Game.HardGame;
import edu.hitsz.application.Game.NormalGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 程序入口
 * @author hitsz
 */
public class Main extends Thread{

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final Object MAIN_LOCK = new Object();
    public static final Object RANK_LOCK = new Object();

    public static String degree;
    public static Game game;


    public static void main(String[] args) throws IOException {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Start start = new Start();
        JPanel StartPanel = start.getMainPanel();
        frame.add(StartPanel);
        frame.setVisible(true);
        synchronized (MAIN_LOCK) {
            try {
                MAIN_LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        switch (Start.gameDegree) {
            case "easy":
                game = new EasyGame();
                degree = "easy";
                break;
            case "normal":
                game = new NormalGame();
                degree = "normal";
                break;
            case "hard":
                game = new HardGame();
                degree = "hard";
                break;
            default:
                game = null;
        }

        frame.remove(StartPanel);
        frame.setVisible(false);
        frame.add(game);
        frame.setVisible(true);


        game.action();
        synchronized (Main.MAIN_LOCK) {
            while (!game.gameOverFlag){
                try {
                    new MusicThread("src/videos/bgm.wav").start();
                    Main.MAIN_LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        ScoreTable scoreTable = new ScoreTable();
        JPanel EndPanel = scoreTable.getMainPanel();

        frame.remove(game);
        frame.setVisible(false);
        frame.add(EndPanel);
        frame.setVisible(true);

        //确认窗口弹出
        InputUsername inputUsername = new InputUsername(Game.getScore(),degree);
        JFrame inputFrame = new JFrame("记录分数");
        inputUsername.setSelf(inputFrame);
        inputUsername.setScoreTable(scoreTable);
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setContentPane(inputUsername.getMainPanel());
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputFrame.setUndecorated(true);
        inputFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        inputFrame.pack();
        inputFrame.setResizable(false);
        inputFrame.setVisible(true);

//        frame.remove(EndPanel);
//        frame.dispose();
    }
}

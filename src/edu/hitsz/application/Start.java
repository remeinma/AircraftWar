package edu.hitsz.application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import static edu.hitsz.application.Main.WINDOW_HEIGHT;
import static edu.hitsz.application.Main.WINDOW_WIDTH;

public class Start {
    public static String gameDegree;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JComboBox musicChoose;
    private JLabel musicLabel;


    public Start() {
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.MAIN_LOCK){
                    try {
                        ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
                        gameDegree = "easy";
                        Main.MAIN_LOCK.notify();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.MAIN_LOCK){
                    try {
                        ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
                        gameDegree = "normal";
                        Main.MAIN_LOCK.notify();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.MAIN_LOCK){
                    try {
                        ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
                        gameDegree = "hard";
                        Main.MAIN_LOCK.notify();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        musicChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (musicChoose.getSelectedItem().equals("开")) {
                        MusicThread.open = true;
                    } else {
                        MusicThread.open = false;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }


}

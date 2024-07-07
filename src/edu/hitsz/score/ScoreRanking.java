package edu.hitsz.score;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreRanking {
    private String name;
    private int score;
    private String time;
    public ScoreRanking(String name,int score, String time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
}

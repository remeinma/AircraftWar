package edu.hitsz.application.Game;


import edu.hitsz.EnemyFactory.*;
import edu.hitsz.aircraft.EnemyAircraft;

import java.util.List;

public class NormalGame extends Game{

    private int enemyMaxNumber = 5;
    private EnemyFactory enemyFactory;
    public double gameLevel = 1;

    @Override
    public double levelUp() {
        if(getTime()%10200==0) {
            gameLevel+=0.02;
            System.out.print("提高难度！精英机概率:");
            System.out.printf("%.2f",0.3*gameLevel);
            System.out.println("  敌机属性提升倍率："+gameLevel);
        }
        return gameLevel;
    }

    @Override
    public void enemyGenerate(List<EnemyAircraft> enemyAircrafts) {
        if (enemyAircrafts.size() < enemyMaxNumber) {
            double rannum = (Math.random());
            if(score>=500*(super.bossnum+1)&&!super.bossflag){
                enemyFactory = new BossFactory();
                bossnum++;
                bossflag = true;
            } else if (rannum <= 0.1*gameLevel) {
                enemyFactory = new ElitePlusFactory();
            } else if (rannum>0.1*gameLevel&&rannum<0.3*gameLevel) {
                enemyFactory = new EliteFactory();
            } else {        //产生普通敌机
                enemyFactory = new MobFactory();
            }
            enemyAircrafts.add(enemyFactory.createEnemy());
        }
    }
}
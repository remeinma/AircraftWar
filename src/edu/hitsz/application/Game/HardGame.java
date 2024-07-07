package edu.hitsz.application.Game;


import edu.hitsz.EnemyFactory.*;
import edu.hitsz.aircraft.EnemyAircraft;

import java.util.List;

public class HardGame extends Game{

    private int enemyMaxNumber = 7;
    private EnemyFactory enemyFactory;

    @Override
    public double levelUp() {
        if(getTime()%10200==0) {
            gameLevel+=0.04;
            System.out.println("提高难度！精英机概率："+0.4*gameLevel+"  敌机属性提升倍率："+gameLevel);
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
            } else if (rannum <= 0.2*gameLevel) {
                enemyFactory = new ElitePlusFactory();
            } else if (rannum>0.2*gameLevel&&rannum<0.4*gameLevel) {
                enemyFactory = new EliteFactory();
            } else {        //产生普通敌机
                enemyFactory = new MobFactory();
            }
            enemyAircrafts.add(enemyFactory.createEnemy());
        }
    }
}
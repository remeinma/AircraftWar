package edu.hitsz.application.Game;


import edu.hitsz.EnemyFactory.*;
import edu.hitsz.aircraft.EnemyAircraft;

import java.util.List;

public class EasyGame extends Game{

    private int enemyMaxNumber = 4;
    private EnemyFactory enemyFactory;

    @Override
    public double levelUp() {
        return gameLevel;
    }

    @Override
    public void enemyGenerate(List<EnemyAircraft> enemyAircrafts) {
        if (enemyAircrafts.size() < enemyMaxNumber) {
            int rannum = (int)(Math.random()*10);
            if(score>=800*(super.bossnum+1)&&rannum==7&&!super.bossflag){
                enemyFactory = new BossFactory();
                bossnum++;
                bossflag = true;
            } else if (rannum == 8) {
                enemyFactory = new ElitePlusFactory();
            } else if (rannum == 9||rannum == 6||rannum == 5) {
                enemyFactory = new EliteFactory();
            } else {        //产生普通敌机
                enemyFactory = new MobFactory();
            }
            enemyAircrafts.add(enemyFactory.createEnemy());
        }
    }
}

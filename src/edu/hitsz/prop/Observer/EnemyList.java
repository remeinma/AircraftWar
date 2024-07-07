package edu.hitsz.prop.Observer;

import edu.hitsz.aircraft.Boss;
import edu.hitsz.aircraft.ElitePlus;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Game.Game;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class EnemyList implements MyObserver{
    private List<BaseBullet> enemyBullets;
    private List<EnemyAircraft> enemyAircrafts;
    public EnemyList(List<BaseBullet> enemyBullets,List<EnemyAircraft> enemyAircrafts) {
        this.enemyAircrafts = enemyAircrafts;
        this.enemyBullets = enemyBullets;
    }
    @Override
    public void response() {
        for(EnemyAircraft enemyAircraft: enemyAircrafts) {
            if(!(enemyAircraft instanceof Boss)) {
                if(enemyAircraft instanceof ElitePlus) {
                    enemyAircraft.decreaseHp(30);
                } else {
                    enemyAircraft.decreaseHp(enemyAircraft.getHp());
                }
                Game.score+=enemyAircraft.Score();
                enemyAircraft.setPropNum(0);
            }
        }
    }
}

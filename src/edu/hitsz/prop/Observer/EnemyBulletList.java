package edu.hitsz.prop.Observer;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class EnemyBulletList implements MyObserver{
    private List<BaseBullet> enemyBullets;
    private List<EnemyAircraft> enemyAircrafts;
    public EnemyBulletList(List<BaseBullet> enemyBullets,List<EnemyAircraft> enemyAircrafts) {
        this.enemyAircrafts = enemyAircrafts;
        this.enemyBullets = enemyBullets;
    }
    @Override
    public void response() {
        enemyBullets.removeAll(enemyBullets);
    }
}

package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class PropBulletPlus extends BaseProp{
    public PropBulletPlus(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void effect(HeroAircraft heroAircraft, List<EnemyAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {
//        interruptThread();
        thread = new Thread(() ->applyEffect(20,heroAircraft));
        thread.start();
    }

//    @Override
//    public void run() {
//        applyEffect(20,HeroAircraft.getHeroAircraft());
//    }
}

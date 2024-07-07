package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class PropBullet extends BaseProp{
    public PropBullet(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void effect(HeroAircraft heroAircraft, List<EnemyAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {
//        interruptThread();
        thread = new Thread(() ->applyEffect(5,heroAircraft));
        thread.start();
    }
    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

//    @Override
//    public void run() {
//        applyEffect(5,HeroAircraft.getHeroAircraft());
//    }
}

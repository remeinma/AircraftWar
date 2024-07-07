package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.List;

public class PropBlood extends BaseProp{


    public PropBlood(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    private int addblood = 20;
    @Override
    public void effect(HeroAircraft heroAircraft, List<EnemyAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {
        new MusicThread("src/videos/get_supply.wav").start();
        heroAircraft.increaseHp(addblood);
    }
    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }


}

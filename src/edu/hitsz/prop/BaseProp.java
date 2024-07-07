package edu.hitsz.prop;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * 所有种类道具的抽象父类：
 * 加血道具、火力道具、炸弹道具
 *
 * @author hitsz
 */
public abstract class BaseProp extends AbstractFlyingObject {


    protected static Object lock = new Object();


    public BaseProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);

    }

    /**
     * 道具效果实现
     */
    public abstract void effect(HeroAircraft heroAircraft, List<EnemyAircraft> enemyAircrafts, List<BaseBullet> enemyBullets);

    protected boolean isBullet = false;
    protected boolean isBulletPlus = false;
    protected Thread thread;
    protected void applyEffect(int newBulletCount, HeroAircraft heroAircraft) {
        synchronized (lock) {
            // 更改弹道数目
            heroAircraft.setShootNum(newBulletCount);
        }
        try {
            thread.sleep(10000); // 道具效果持续的时间
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        synchronized (lock) {
            // 恢复原始弹道数目
            heroAircraft.setShootNum(1);

        }
    }

}

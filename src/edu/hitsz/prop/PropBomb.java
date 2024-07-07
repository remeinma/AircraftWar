package edu.hitsz.prop;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.Observer.BombSupply;
import edu.hitsz.prop.Observer.EnemyBulletList;
import edu.hitsz.prop.Observer.EnemyList;
import edu.hitsz.prop.Observer.MyObserver;

import java.util.List;

public class PropBomb extends BaseProp{
    public PropBomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void effect(HeroAircraft heroAircraft, List<EnemyAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {
        new MusicThread("src/videos/bomb_explosion.wav").start();
        System.out.println("BombSupply active!");
        BombSupply bombSupply = new BombSupply();
        MyObserver ob1,ob2;
        ob1 = new EnemyBulletList(enemyBullets,enemyAircrafts);
        ob2 = new EnemyList(enemyBullets,enemyAircrafts);
        bombSupply.addObserver(ob1);
        bombSupply.addObserver(ob2);
        bombSupply.notice();
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

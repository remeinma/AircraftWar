package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

import static edu.hitsz.application.Main.game;

public class StraightShoot implements ShootStrategy {
    @Override
    public List<BaseBullet> doshoot(AbstractAircraft abstractAircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int direction = abstractAircraft.getDirection();
        int power = abstractAircraft.getPower();
        int shootNum = abstractAircraft.getShootNum();
        int x = abstractAircraft.getLocationX();
        int y = abstractAircraft.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = abstractAircraft.getSpeedY() + direction*5;
        BaseBullet bullet;
        if( abstractAircraft instanceof HeroAircraft)
        {
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                res.add(bullet);
            }
        }
        else {
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, (int)(speedY*game.gameLevel), power);
                res.add(bullet);
            }
        }
        return res;
    }
}

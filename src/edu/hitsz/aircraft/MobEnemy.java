package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends EnemyAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
    @Override
    public int getDirection() {
        return 0;
    }

    @Override
    public int getShootNum() {
        return 0;
    }

    @Override
    public int getPower() {
        return 0;
    }

    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }
    private int propNum = 0;
    @Override
    public int getPropNum() {
        return propNum;
    }
    @Override
    public void setPropNum(int num){
        this.propNum = num;
    }

    @Override
    public int Score() {
        return 10;
    }

}

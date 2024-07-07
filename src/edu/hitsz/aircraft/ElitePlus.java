package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.strategy.SanShoot;
import edu.hitsz.strategy.StraightShoot;

import java.util.LinkedList;
import java.util.List;

public class ElitePlus extends EnemyAircraft{

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 10;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;
    public ElitePlus(int locationX, int locationY, int speedX, int speedY, int hp) {
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
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        if(shootNum==1){
            this.setShootStrategy(new StraightShoot());
        }
        else {
            this.setShootStrategy(new SanShoot());
        }
        return this.executeStrategy(this);
    }
    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public int getShootNum() {
        return shootNum;
    }

    @Override
    public int getPower() {
        return power;
    }
    private int propNum = 1;
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
        return 30;
    }

}

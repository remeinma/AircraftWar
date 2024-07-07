package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class Boss extends EnemyAircraft{
    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 20;

    /**
     * 子弹伤害
     */
    private int power = 5;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;
    private int propNum = 3;
    public Boss(int locationX, int locationY, int speedX, int speedY, int hp) {
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
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY();
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x+(int) (Math.cos(Math.PI*((double) i /20)*2)*20), y+(int) (Math.sin(Math.PI*((double) i /20)*2)*20), (int) (Math.cos(Math.PI*((double) i /20)*2)*direction*8), (int) (Math.sin(Math.PI*((double) i /20)*2)*direction*8), power);
            res.add(bullet);
        }
        return res;
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
        return 100;
    }
}

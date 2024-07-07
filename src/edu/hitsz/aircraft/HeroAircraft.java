package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.strategy.CircleShoot;
import edu.hitsz.strategy.SanShoot;
import edu.hitsz.strategy.StraightShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 50;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */

    /**
     * 单例模式创造英雄机
     * @return   英雄机
     */
    private volatile static HeroAircraft heroAircraft;
    HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    public static HeroAircraft getHeroAircraft() {
        if (heroAircraft == null) {
            synchronized (HeroAircraft.class) {
                if (heroAircraft == null) {
                    heroAircraft = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 1000);
                }
            }
        }
        return heroAircraft;
    }
    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        if(this.getShootNum()==1){
            this.setShootStrategy(new StraightShoot());
        }
        else if(this.getShootNum()==20){
            this.setShootStrategy(new CircleShoot());
        }
        else{
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


    public void setShootNum(int num){
        shootNum = num;
    }

}

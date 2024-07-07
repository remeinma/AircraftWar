package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public abstract class EnemyAircraft extends AbstractAircraft{
    private int propNum;

    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public abstract List<BaseBullet> shoot();

    public abstract int getPropNum();
    public abstract void setPropNum(int num);


    public abstract int Score();
}

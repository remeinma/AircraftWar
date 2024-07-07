package edu.hitsz.PropFactory;

import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.PropBlood;
import edu.hitsz.prop.PropBullet;

public class BulletFactory implements PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY) {
        return new PropBullet(locationX,
                locationY, 0, 5);
    }
}

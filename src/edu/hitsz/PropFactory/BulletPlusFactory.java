package edu.hitsz.PropFactory;

import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.PropBulletPlus;

public class BulletPlusFactory implements PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY) {
        return new PropBulletPlus(locationX,
                locationY, 0, 5);
    }
}

package edu.hitsz.PropFactory;

import edu.hitsz.application.MusicThread;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.PropBlood;
import edu.hitsz.prop.PropBomb;

public class BombFactory implements PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY) {
        return new PropBomb(locationX,
                locationY, 0, 5);
    }
}

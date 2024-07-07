package edu.hitsz.PropFactory;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.PropBlood;

public class BloodFactory implements PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY) {
        return new PropBlood(locationX,
                locationY, 0, 5);
    }
}

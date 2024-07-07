package edu.hitsz.PropFactory;

import edu.hitsz.prop.BaseProp;

public interface PropFactory {
    public abstract BaseProp createProp(int locationX, int locationY);
}

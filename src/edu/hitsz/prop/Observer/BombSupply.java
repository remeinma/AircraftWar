package edu.hitsz.prop.Observer;

import java.util.ArrayList;

public class BombSupply {
    protected ArrayList observers = new ArrayList();
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void notice() {
        for (Object obs:observers){
            ((MyObserver)obs).response();
        }
    }
}

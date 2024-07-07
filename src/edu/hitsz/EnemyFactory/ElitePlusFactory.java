package edu.hitsz.EnemyFactory;

import edu.hitsz.aircraft.ElitePlus;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import static edu.hitsz.application.Main.game;

public class ElitePlusFactory implements EnemyFactory{
    @Override
    public EnemyAircraft createEnemy() {
        return new ElitePlus((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITEPLUS_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                1,
                (int)(4*game.gameLevel),
                70+(int)(1000*(game.gameLevel-1)));
    }
}

package edu.hitsz.EnemyFactory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import static edu.hitsz.application.Main.game;

public class EliteFactory implements EnemyFactory{
    @Override
    public EnemyAircraft createEnemy() {

        return new EliteEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                (int)(4*game.gameLevel),
                40+(int)(1000*(game.gameLevel-1)));
    }
}

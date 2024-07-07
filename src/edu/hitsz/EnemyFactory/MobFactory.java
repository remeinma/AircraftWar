package edu.hitsz.EnemyFactory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import static edu.hitsz.application.Main.game;

public class MobFactory implements EnemyFactory{
    @Override
    public EnemyAircraft createEnemy() {
        return new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                (int)(4*game.gameLevel),
                30+(int)(1000*(game.gameLevel-1)));
    }
}

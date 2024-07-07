package edu.hitsz.EnemyFactory;

import edu.hitsz.aircraft.Boss;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;

import static edu.hitsz.application.Main.game;


public class BossFactory implements EnemyFactory{
    @Override
    public EnemyAircraft createEnemy() {
        MusicThread.bgBgm = false;
        MusicThread.bossBgm = true;
        new MusicThread("src/videos/bgm_boss.wav").start();
        return new Boss((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05+0.5*ImageManager.BOSS_ENEMY_IMAGE.getHeight()),
                2,
                0,
                1000+(int)(10000*(game.gameLevel-1)));
    }
}

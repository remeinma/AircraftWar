package edu.hitsz.aircraft;

import edu.hitsz.EnemyFactory.EliteFactory;
import edu.hitsz.EnemyFactory.EnemyFactory;
import edu.hitsz.PropFactory.BloodFactory;
import edu.hitsz.PropFactory.PropFactory;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    HeroAircraft heroAircraft;
    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        heroAircraft = HeroAircraft.getHeroAircraft();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
    }

    @Test
    void crash() {
        System.out.println("**--- Test crash method executed ---**");
        BaseProp baseProp = null;
        PropFactory propFactory = null;
        propFactory = new BloodFactory();
        baseProp = propFactory.createProp(heroAircraft.getLocationX(), heroAircraft.getLocationY());
        assertTrue(heroAircraft.crash(baseProp));
    }

    @Test
    void forward() {
        System.out.println("**--- Test forward method executed ---**");
        EnemyFactory enemyFactory = null;
        enemyFactory = new EliteFactory();
        EnemyAircraft enemyAircraft = enemyFactory.createEnemy();
        int x = enemyAircraft.getLocationX();
        int y = enemyAircraft.getLocationY();
        enemyAircraft.forward();
        assertEquals(x+enemyAircraft.getSpeedX(),enemyAircraft.getLocationX());
        assertEquals(y+enemyAircraft.getSpeedY(),enemyAircraft.getLocationY());
    }

    @Test
    void shoot() {
        System.out.println("**--- Test shoot method executed ---**");
        List<BaseBullet> res = heroAircraft.shoot();
        assertNotNull(res);
    }

    @Test
    void decreaseHp() {
        System.out.println("**--- Test decreaseHp method executed ---**");
        int Hp = heroAircraft.hp;
        heroAircraft.decreaseHp(20);
        assertEquals(Hp-20,heroAircraft.hp);
    }
}
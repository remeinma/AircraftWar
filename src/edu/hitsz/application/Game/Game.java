package edu.hitsz.application.Game;

import edu.hitsz.EnemyFactory.*;
import edu.hitsz.PropFactory.*;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.Boss;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.score.ScoreRanking;
import edu.hitsz.score.ScoreRankingDaoImpl;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static edu.hitsz.application.MusicThread.bgBgm;
import static edu.hitsz.application.MusicThread.bossBgm;

public abstract class Game extends JPanel {

    public double gameLevel = 1;
    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<EnemyAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<BaseProp> props;

    /**
     * 屏幕中出现的敌机最大数量
     */
    private int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    public static int score = 0;

    public String testUserName = "testUserName";
    /**
     * 当前时刻
     */
    private int time = 0;
    public int getTime(){
        return time;
    }

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;
    int bossnum = 0;
    boolean bossflag = false;

    /**
     * 游戏结束标志
     */
    public boolean gameOverFlag = false;

    MusicThread bgm;
    MusicThread bossBgm;

    public abstract double levelUp();

    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController((Game) this, heroAircraft);

    }

    public static int getScore() {
        return score;
    }


    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                levelUp();

                // 新敌机产生
                //产生精英敌机
//                EnemyFactory enemyFactory = null;
//                if (enemyAircrafts.size() < enemyMaxNumber) {
//                    int rannum = (int)(Math.random()*10);
//                    if(score>=500*(bossnum+1)&&rannum==7&&!bossflag){
//                        enemyFactory = new BossFactory();
//                        bossnum++;
//                        bossflag = true;
//                    } else if (rannum == 8) {
//                        enemyFactory = new ElitePlusFactory();
//                    } else if (rannum == 9||rannum == 6||rannum == 5) {
//                        enemyFactory = new EliteFactory();
//                    } else {        //产生普通敌机
//                        enemyFactory = new MobFactory();
//                    }
//                    enemyAircrafts.add(enemyFactory.createEnemy());
//                }
                enemyGenerate(enemyAircrafts);
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();


            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                new MusicThread("src/videos/game_over.wav").start();
                bgBgm = false;
                MusicThread.bossBgm = false;
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
//                try {
//                    Print(score,testUserName,gameOverFlag);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println("Game Over!");
                synchronized (Main.MAIN_LOCK) {
                    Main.MAIN_LOCK.notify();
                }
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }


    //***********************
    //      Action 各部分
    //***********************
    public abstract void enemyGenerate(List<EnemyAircraft> enemyAircrafts);

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for (AbstractAircraft enemyAircraft : enemyAircrafts){
            enemyBullets.addAll(enemyAircraft.shoot());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }
    private void propsMoveAction(){
        for (BaseProp prop : props) {
            prop.forward();
        }
    }

    /**
     * 道具生成：
     * 1.随机生成三种道具
     */

    private void propsGenerate(List<BaseProp> props, EnemyAircraft enemyAircraft){
        for(int i = 0; i < enemyAircraft.getPropNum(); i++)
        {
            PropFactory propFactory = null;
            int ranprop = (int) (Math.random() * 5);
            switch (ranprop) {
                case 0:
                    propFactory = new BloodFactory();
                    props.add(propFactory.createProp((int) (enemyAircraft.getLocationX()+ ImageManager.PROP_BULLET_IMAGE.getWidth()*1.5),enemyAircraft.getLocationY()));
                    break;
                case 1:
                    propFactory = new BombFactory();
                    props.add(propFactory.createProp((int) (enemyAircraft.getLocationX()-ImageManager.PROP_BULLET_IMAGE.getWidth()*1.5),enemyAircraft.getLocationY()));
                    break;
                case 2:
                    propFactory = new BulletFactory();
                    props.add(propFactory.createProp(enemyAircraft.getLocationX()+ImageManager.PROP_BULLET_IMAGE.getWidth(),enemyAircraft.getLocationY()));
                    break;
                case 3:
                    propFactory = new BulletPlusFactory();
                    props.add(propFactory.createProp(enemyAircraft.getLocationX()-ImageManager.PROP_BULLET_IMAGE.getWidth(),enemyAircraft.getLocationY()));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets)
        {
            if(bullet.notValid()){
                continue;
            }
            if(heroAircraft.crash(bullet)){
                //战机被射中
                //损失生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (EnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    new MusicThread("src/videos/bullet_hit.wav").start();
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        score += enemyAircraft.Score();
                        if(enemyAircraft instanceof Boss) {
                            MusicThread.bossBgm = false;
                            MusicThread.bgBgm = true;
                            new MusicThread("src/videos/bgm.wav").start();
                            bossflag = false;
                        }
                        propsGenerate(props, enemyAircraft);
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (BaseProp prop : props){
            if(prop.notValid()){
                continue;
            }
            if (heroAircraft.crash(prop)){
                //战机得到道具
                prop.effect(heroAircraft,enemyAircrafts,enemyBullets);
                prop.vanish();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    /**
     * 记录分数排名
     */
    public void Print(int score,String name, boolean flag) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String time = format.format(new Date());
        ScoreRankingDaoImpl scoreRankingDao = new ScoreRankingDaoImpl();
        scoreRankingDao.readFile();
        scoreRankingDao.clearFile();
        if(flag){
            scoreRankingDao.doAdd(new ScoreRanking(name, score, time));
        }
        List<ScoreRanking> scoreRankings = scoreRankingDao.getAllScores();

        System.out.println("*********************************************************");
        System.out.println("                        得分排行榜                         ");
        System.out.println("*********************************************************");
        int index = 1;
        for(ScoreRanking scoreRanking : scoreRankings) {
            System.out.printf("第%d名：%s,%d,%s\n",index,scoreRanking.getName(),scoreRanking.getScore(),scoreRanking.getTime());
            index++;
        }
        return;
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，再绘制道具，后绘制飞机,
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, props);

        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }



}

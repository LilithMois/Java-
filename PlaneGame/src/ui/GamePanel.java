package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {

    BufferedImage bg;
    Hero hero = new Hero();
    List<Ep> eps = new ArrayList<Ep>();
    List<Fire> fires = new ArrayList<Fire>();
    List<StageProperty> StagePropertys = new ArrayList<StageProperty>();
    int score;
    int power = 0;
    boolean gameover = false;


    public void action() {
        new Thread() {
            public void run() {
                while (true) {
                    if (!gameover) {
                        epEnter();
                        epMove();
                        stagePropertyEnter();
                        stagePropertyMove();
                        shoot();
                        fireMove();
                        shootEp();
                        hit();
                        addLife();
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        }.start();
    }

    int stages = 0;

    protected void stagePropertyEnter() {
        stages++;
        if (stages >= 1000) {
            StageProperty sp = new StageProperty();
            StagePropertys.add(sp);
            stages = 0;
        }
    }


    protected void stagePropertyMove() {
        for (int i = 0; i < StagePropertys.size(); i++) {
            StageProperty sp = StagePropertys.get(i);
            sp.move();
        }
    }

    protected void shootEp() {
        for (int i = 0; i < fires.size(); i++) {
            Fire f = fires.get(i);
            bang(f);
        }
    }

    protected void addLife() {
        for (int i = 0; i < StagePropertys.size(); i++) {
            StageProperty sp = StagePropertys.get(i);
            if (sp.addBy(hero)) {
                StagePropertys.remove(sp);
                if (power < 2) {
                    power++;
                } else {
                    hero.hp++;
                }
            }
        }
    }

    protected void hit() {
        for (int i = 0; i < eps.size(); i++) {
            Ep e = eps.get(i);
            if (e.hitBy(hero)) {
                eps.remove(e);
                hero.hp--;
                power--;
                if (power < 0) {
                    power = 0;
                }
                if (hero.hp <= 0) {
                    gameover = true;
                }
            }
        }
    }

    protected void bang(Fire f) {
        for (int i = 0; i < eps.size(); i++) {
            Ep e = eps.get(i);
            if (e.shootBy(f)) {
                e.hp--;
                if (e.hp <= 0) {
                    eps.remove(e);
                    score += e.sp;
                }
                fires.remove(f);
            }
        }
    }

    protected void fireMove() {
        for (int i = 0; i < fires.size(); i++) {
            Fire f = fires.get(i);
            f.move();
        }
    }

    int findex = 0;

    protected void shoot() {
        findex++;
        if (findex >= 10) {
            switch (power) {
                case 0:
                    Fire fire3 = new Fire(hero.x + 45, hero.y - 20);
                    fires.add(fire3);
                    break;
                case 2:
                        Fire fire4 = new Fire(hero.x + 45, hero.y - 20);
                    fires.add(fire4);
                case 1 :
                    Fire fire1 = new Fire(hero.x + 13, hero.y);
                    fires.add(fire1);
                    Fire fire2 = new Fire(hero.x + 75, hero.y);
                    fires.add(fire2);

            }
            findex = 0;
        }
    }

    protected void epMove() {
        for (int i = 0; i < eps.size(); i++) {
            Ep e = eps.get(i);
            e.move();
        }
    }

    int index = 0;

    protected void epEnter() {
        index++;
        if (index >= 30) {
            Ep e = new Ep();
            eps.add(e);
            index = 0;
        }
    }

    public GamePanel() {
        setBackground(Color.BLACK);

        bg = App.getImg("/img/bg2.jpg");
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameover) {
                    hero = new Hero();
                    score = 0;
                    eps.clear();
                    fires.clear();
                    StagePropertys.clear();
                    gameover = !gameover;
                    Random rd = new Random();
                    int index = rd.nextInt(5) + 1;
                    bg = App.getImg("/img/bg" + index + ".jpg");
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();
                if (!gameover) {
                    hero.moveToMouse(mx, my);
                }
                repaint();
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bg, 0, 0, 512, 768, null);
        g.drawImage(hero.img, hero.x, hero.y, hero.w, hero.h, null);
        for (int i = 0; i < eps.size(); i++) {
            Ep ep = eps.get(i);
            g.drawImage(ep.img, ep.x, ep.y, ep.w, ep.h, null);
        }
        for (int i = 0; i < fires.size(); i++) {
            Fire fire = fires.get(i);
            g.drawImage(fire.img, fire.x, fire.y, fire.w, fire.h, null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("楷体", Font.BOLD, 20));
        g.drawString("分数:" + score, 10, 30);
        for (int i = 0; i < hero.hp; i++) {
            g.drawImage(hero.img, 350 + i * 35, 5, 30, 30, null);
        }
        if (gameover) {
            g.setColor(Color.red);
            g.setFont(new Font("楷体", Font.BOLD, 50));
            g.drawString("GAMEOVER!", 135, 300);
            g.setColor(Color.black);
            g.setFont(new Font("楷体", Font.BOLD, 40));
            g.drawString("点击屏幕重新开始游戏", 35, 350);
        }
        for (int i = 0; i < StagePropertys.size(); i++) {
            StageProperty sp = StagePropertys.get(i);
            g.drawImage(sp.img, sp.x, sp.y, sp.w, sp.h, null);
        }
    }
}

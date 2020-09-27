package ui;

import java.util.Random;

public class Ep extends FlyObject {

    int sp;
    int hp;

    public Ep() {
        Random rd = new Random();
        int index = rd.nextInt(14) + 1;
        String path = "/img/ep" + (index < 10 ? "0" : "") + index + ".png";
        img = App.getImg(path);

        w = img.getWidth();
        h = img.getHeight();
        x = rd.nextInt(512 - w);
        y = -h;
        sp = 17 - index;
        hp = index;
    }

    public void move() {
        if (sp >= 10) {
            sp = 10;
        }
        y += sp;
    }

    public boolean shootBy(Fire f) {
        boolean hit = x <= f.x + f.w &&
                x >= f.x - w &&
                y <= f.y + f.h &&
                y >= f.y - h;
        return hit;
    }

    public boolean hitBy(Hero f) {
        boolean hit = x <= f.x + f.w - 50 &&
                x >= f.x - w + 50 &&
                y <= f.y + f.h - 50 &&
                y >= f.y - h + 50;
        return hit;
    }
}


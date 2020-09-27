package ui;

import java.util.Random;

public class StageProperty extends FlyObject {
    boolean flag = false;

    public StageProperty() {
        img = App.getImg("/img/score.png");
        Random rd = new Random();
        w = img.getWidth();
        h = img.getHeight();
        x = rd.nextInt(470) + 20;
        y = -h;

    }

    public void move() {
        y += 2;
        x += 2;
        if (x > 500) {
            flag = true;
        }
        if (flag) {
            x -= 4;
        }
    }

    public boolean addBy(Hero f) {
        boolean hit = x <= f.x + f.w - 50 &&
                x >= f.x - w + 50 &&
                y <= f.y + f.h - 50 &&
                y >= f.y - h + 50;
        return hit;
    }
}

package ui;


public class Hero extends FlyObject {
    int hp;

    public Hero() {
        img = App.getImg("/img/hero.png");
        x = 200;
        y = 500;
        w = img.getWidth();
        h = img.getHeight();
        hp = 3;
    }

    public void moveToMouse(int mx, int my) {
        x = mx - w / 2;
        y = my - h / 2;

    }
}

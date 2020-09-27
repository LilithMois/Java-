package ui;

public class Fire extends FlyObject {
    public Fire(int hx, int hy) {
        img = App.getImg("/img/fire.png");
        w = img.getWidth() / 4;
        h = img.getHeight() / 4;
        x = hx;
        y = hy;
    }

    public void move() {
        y -= 10;
    }
}

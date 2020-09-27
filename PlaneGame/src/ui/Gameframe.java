package ui;

import javax.swing.*;

public class Gameframe extends JFrame {
    public Gameframe() {
        setTitle("PlaneGame");
        setSize(512, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Gameframe frame = new Gameframe();
        GamePanel panel = new GamePanel();
        panel.action();
        frame.add(panel);
        frame.setVisible(true);
    }
}



package CoffeeShop.games;

import javax.swing.*;
  public class GameFrame extends JFrame {
    public GameFrame() {
        init();
    }
      public void init() {
        this.setTitle("Game");
        //添加面板
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        PlayGamePanel play = new PlayGamePanel();
        add(play);
        play.startRun();
    }
}
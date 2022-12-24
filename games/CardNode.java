package CoffeeShop.games;

import javax.swing.*;
  public class CardNode extends JLabel {
    private int type;
    private int level;
    private int xIndex;
    private int yIndex;
      public CardNode(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }
      public int getType() {
        return type;
    }
      public void setType(int type) {
        this.type = type;
    }
      public int getLevel() {
        return level;
    }
      public void setLevel(int level) {
        this.level = level;
    }
      public int getxIndex() {
        return xIndex;
    }
      public void setxIndex(int xIndex) {
        this.xIndex = xIndex;
    }
      public int getyIndex() {
        return yIndex;
    }
      public void setyIndex(int yIndex) {
        this.yIndex = yIndex;
    }
}
package CoffeeShop.games;

import java.awt.*;
  public interface BaseData {
      Font topicFont = new Font("TimeRoman", Font.BOLD, 50);
      Font scoreFont = new Font("TimeRoman", Font.BOLD, 28);
      Font normalFont = new Font("TimeRoman", Font.PLAIN, 20);
      Font font1 = new Font("TimeRoman", Font.BOLD, 46);
      Font font2 = new Font("TimeRoman", Font.BOLD, 40);
      Font font3 = new Font("TimeRoman", Font.BOLD, 34);
      Font font4 = new Font("TimeRoman", Font.BOLD, 28);
      Font font5 = new Font("TimeRoman", Font.BOLD, 22);
      int normalFontData = 20;
      int topicFontData = 30;
      void init();
      void showView();
  }
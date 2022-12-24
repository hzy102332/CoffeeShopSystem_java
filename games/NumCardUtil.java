package CoffeeShop.games;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
  public class NumCardUtil {
    /**随机卡片数组*/
    public static int[] cardsCount = new int[9];
    /**所有卡片数字存储*/
    public static List<Integer> cardsList = new ArrayList<>();
    /**卡片name存储*/
    public static List<String> centerCardData = new ArrayList<>();
      /**中间卡片*/
    public static List<CardNode> centerCards = new ArrayList<>();
    /**左边卡片*/
    public static List<CardNode> leftCards1 = new ArrayList<>();
    public static List<CardNode> leftCards2 = new ArrayList<>();
    /**右边卡片*/
    public static List<CardNode> rightCards1 = new ArrayList<>();
    public static List<CardNode> rightCards2 = new ArrayList<>();
      /**
     * 初始化卡片数字
     */
    public static void initCards() {
        clearCards();
          //初始化数字卡片
        Random r = new Random();
        int num;
        for (int i = 0; i < 93; i++) {
            num = r.nextInt(8) + 1;
            cardsCount[num]++;
            cardsList.add(num);
        }
          initCardList(centerCards, 55);
        initCardList(leftCards1, 9);
        initCardList(leftCards2, 10);
        initCardList(rightCards1, 9);
        initCardList(rightCards2, 10);
    }
      /**
     * 初始化卡片对象
     * @param list
     * @param cardCount
     */
    public static void initCardList(List<CardNode> list, int cardCount) {
        Random r = new Random();
        int index;
        CardNode numLabel;
//        System.out.println(cardsList);
        for (int i = 0; i < cardCount; i++) {
            index = r.nextInt(cardsList.size());
            numLabel = new CardNode(String.valueOf(cardsList.get(index)), JLabel.CENTER);
            list.add(numLabel);
            cardsList.remove(index);
        }
    }
      /**
     * 根据层级个数生成图片，固定为5，可拓展
     * @param level
     */
    public static void initcenterCardData(int level) {
          for (int n = 1; n < level; n++) {
            for (int i = 0, x = 0, y = -1; i < n * n; i++) {
                if (i % n == 0) {
                    x = 0;
                    y++;
                }
                centerCardData.add("center-" + n + "-" + x + "-" + y);
                x++;
            }
        }
    }
      /**
     * 清空数据
     */
    public static void clearCards() {
        centerCardData.clear();
        centerCards.clear();
        leftCards1.clear();
        leftCards2.clear();
        rightCards1.clear();
        rightCards2.clear();
    }
}
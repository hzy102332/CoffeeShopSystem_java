package CoffeeShop.games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
  public class PlayGamePanel extends JPanel implements MouseListener {
    private int[] nodeCount = new int[9];
    private int[][] bottomPoints = new int[6][2];
    private List<CardNode> bottomNodeList = new ArrayList<>();
    private JPanel bottomPanel = new JPanel();
    private JLabel tips = new JLabel("Game in progress...", JLabel.CENTER);
      public PlayGamePanel() {
        this.setLayout(null);
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(150, 480, 500, 80);
        bottomPanel.setBackground(Color.LIGHT_GRAY);
          tips.setFont(new Font("Dialog", Font.PLAIN, 20));
        tips.setBounds(150, 370, 500, 80);
        add(tips);
          initCardLabel();
        //初始化底部卡槽背景
        for (int i = 6; i > 0; i--) {
            JLabel emptyLabel = new JLabel();
            emptyLabel.setOpaque(true);
            emptyLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            emptyLabel.setBackground(Color.GRAY);
            emptyLabel.setBounds(70 * i - 20, 15, 50, 50);
            bottomPanel.add(emptyLabel);
        }
        //初始化底部6个卡槽坐标
        for (int x = 0; x < 6; x++) {
            bottomPoints[x][0] = 200 + x * 70;
            bottomPoints[x][1] = 495;
        }
          add(bottomPanel);
    }
      public void initCardLabel() {
        NumCardUtil.initCards();
        NumCardUtil.initcenterCardData(5);
          int curIndex = 0;
        int curLineNum = 0;
        int nextLineNum = 0;
          for (int n = 1; n <= 5; n++) {
            for (int i = 0; i < n * n; i++) {
                if (i % n == 0) {
                    nextLineNum++;
                    curLineNum = 0;
                }
                CardNode node = createNumLabel("center-" + n + "-" + curLineNum + "-" + (nextLineNum - 1), NumCardUtil.centerCards.get(curIndex++), 0, 395 - n * 25 + 50 * curLineNum, 125 - 25 * n + 50 * nextLineNum);
                node.setxIndex(curLineNum);
                node.setyIndex(nextLineNum - 1);
                node.setLevel(n);
                  curLineNum++;
            }
            curLineNum = 0;
            nextLineNum = 0;
        }
          //底部卡片列表
        for (int i = 8; i > 0; i--) {
            //NumCardsUtil.leftCards
            createNumLabel("left1-" + i, NumCardUtil.leftCards1.get(i), 1, 20 + 15 * i, 400);
            createNumLabel("right1-" + i, NumCardUtil.rightCards1.get(i), 2, 730 - 15 * i, 400);
        }
          //中部卡片列表
        for (int i = 9; i > 0; i--) {
            //NumCardsUtil.leftCards
            createNumLabel("left2-" + i, NumCardUtil.leftCards2.get(i), 3, 150, 180 + 15 * i);
            createNumLabel("right2-" + i, NumCardUtil.rightCards2.get(i), 4, 600, 180 + 15 * i);
        }
    }
      /**
     *
     * @param numLabel
     * @param x
     * @param y
     */
    public CardNode createNumLabel(String name, CardNode numLabel, int type, int x, int y) {
        numLabel.setName(name);
        numLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        numLabel.setOpaque(true);
        numLabel.setType(type);
          numLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        numLabel.setBackground(Color.GRAY);
        numLabel.setBounds(x, y, 50, 50);
        numLabel.setPreferredSize(new Dimension(50, 50));
        numLabel.setVerticalTextPosition(JLabel.BOTTOM);
        numLabel.setHorizontalTextPosition(JLabel.CENTER);
        numLabel.addMouseListener(this);
          add(numLabel);
        return numLabel;
    }
      /**
     * 刷新面板
     */
    public void startRun() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //刷新屏幕，自动调用paint()方法
                    repaint();
                }
            }
        }.start();
    }
      /**
     * 是否存在父级
     * @param level
     * @param x
     * @param y
     * @return
     */
    public boolean hasParentLabel(int level, int x, int y) {
        String[] ids = {level + "-" + x + "-" + y, level + "-" + (x - 1) + "-" + y, level + "-" + x + "-" + (y - 1), level + "-" + (x - 1) + "-" + (y - 1)};
        for (String id : ids) {
            if (NumCardUtil.centerCardData.contains("center-" + id)) {
                return true;
            }
        }
        return false;
    }
      public void dealUnCenterCardList(List<CardNode> list, CardNode node) {
        CardNode lastNode = list.get(list.size() - 1);
        if (lastNode != node) {
            return;
        }
        list.remove(node);
        orderList(node);
    }
      /**
     * 重排序
     * @param node
     */
    private void orderList(CardNode node) {
        int curValue = Integer.parseInt(node.getText());
        nodeCount[curValue]++;
        if (bottomNodeList.size() == 0) {
            bottomNodeList.add(node);
            node.setLocation(bottomPoints[0][0], bottomPoints[0][1]);
            return;
        }
        int curIndex = 0;
        for (CardNode n : bottomNodeList) {
            int tempValue = Integer.parseInt(n.getText());
            if (curValue >= tempValue) {
                curIndex++;
            } else {
                break;
            }
        }
        bottomNodeList.add(curIndex, node);
        for (int j = 0; j < bottomNodeList.size(); j++) {
            bottomNodeList.get(j).setLocation(bottomPoints[j][0], bottomPoints[j][1]);
        }
          if (nodeCount[curValue] >= 3) {
            nodeCount[curValue] = 0;
            for (int i = 0; i < 3; i++) {
                bottomNodeList.get(curIndex).setVisible(false);
                bottomNodeList.remove(curIndex);
                if (curIndex > 0) {
                    curIndex--;
                }
            }
        }
        for (int j = 0; j < bottomNodeList.size(); j++) {
            bottomNodeList.get(j).setLocation(bottomPoints[j][0], bottomPoints[j][1]);
        }
    }
      @Override
    public void mouseClicked(MouseEvent e) {
      }
      @Override
    public void mousePressed(MouseEvent e) {
      }
      @Override
    public void mouseReleased(MouseEvent e) {
          Component com = e.getComponent();
        if (com instanceof CardNode) {
            CardNode node = (CardNode) com;
            switch (node.getType()) {
                case 0:
                    boolean flag = hasParentLabel(node.getLevel() - 1, node.getxIndex(), node.getyIndex());
                    if (!flag) {
                        NumCardUtil.centerCardData.remove(node.getName());
                        orderList(node);
                    }
                    break;
                case 1:
                    dealUnCenterCardList(NumCardUtil.leftCards1, node);
                    break;
                case 2:
                    dealUnCenterCardList(NumCardUtil.rightCards1, node);
                    break;
                case 3:
                    dealUnCenterCardList(NumCardUtil.leftCards2, node);
                    break;
                case 4:
                    dealUnCenterCardList(NumCardUtil.rightCards2, node);
                    break;
                default:
                    break;
            }
              if (bottomNodeList.size() >= 6) {
                this.removeAll();
                tips.setText("Game Over！");
                tips.setForeground(Color.RED);
                this.add(tips);
                this.setEnabled(false);
                JButton bt = new JButton("Retry");
                bt.setBounds(300,300,180,60);
                bt.setFont(new Font("Dialog", Font.BOLD, 18));
                bt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
//                                Main.gm = new GameFrame();
                            }
                        });
//                        Main.gm.dispose();
                    }
                });
                this.add(bt);
              }
        }
    }
      @Override
    public void mouseEntered(MouseEvent e) {
      }
    @Override
    public void mouseExited(MouseEvent e) {
      }
}
package CoffeeShop.games;

import CoffeeShop.CoffeeShopSystem;
import CoffeeShop.LogInMenu;
import CoffeeShop.RegisterMenu;
import CoffeeShop.Registered;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class G2048GameView implements BaseData {
    private static final int jFrameWidth = 410;
    private static final int jFrameHeight = 530;
    private static int score = 0;
    private JFrame jFrameMain;
    private JLabel jLblTitle;
    private JLabel jLblScoreName;
    private JLabel jLblScore;
    private GameBoard gameBoard;
    private JLabel jlblTip;

    public G2048GameView() {
        init();
    }

    @Override
    public void init() {
        jFrameMain = new JFrame("2048");
        jFrameMain.setSize(jFrameWidth, jFrameHeight);
        jFrameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameMain.setLocationRelativeTo(null);
        jFrameMain.setResizable(false);
        jFrameMain.setLayout(null);
        jLblTitle = new JLabel("2048", JLabel.CENTER);
        jLblTitle.setFont(topicFont);
        jLblTitle.setForeground(Color.BLACK);
        jLblTitle.setBounds(50, 0, 150, 60);
        jFrameMain.add(jLblTitle);
        // 分数区
        jLblScoreName = new JLabel("Score: ", JLabel.CENTER);
        jLblScoreName.setFont(scoreFont);
        jLblScoreName.setForeground(Color.WHITE);
        jLblScoreName.setOpaque(true);
        jLblScoreName.setBackground(Color.GRAY);
        jLblScoreName.setBounds(250, 0, 120, 30);
        jFrameMain.add(jLblScoreName);
        jLblScore = new JLabel("0", JLabel.CENTER);
        jLblScore.setFont(scoreFont);
        jLblScore.setForeground(Color.WHITE);
        jLblScore.setOpaque(true);
        jLblScore.setBackground(Color.GRAY);
        jLblScore.setBounds(250, 30, 120, 30);
        jFrameMain.add(jLblScore);
        jlblTip = new JLabel("Operation: ↑ ↓ ← →, Esc to restart  ",
                JLabel.CENTER);
        jlblTip.setFont(normalFont);
        jlblTip.setForeground(Color.DARK_GRAY);
        jlblTip.setBounds(0, 60, 400, 40);
        jFrameMain.add(jlblTip);
        gameBoard = new GameBoard();
        gameBoard.setBounds(0, 100, 400, 400);
        gameBoard.setBackground(Color.GRAY);
        gameBoard.setFocusable(true);
        gameBoard.setLayout(new FlowLayout());
        jFrameMain.add(gameBoard);
    }

    @SuppressWarnings("serial")
    class GameBoard extends JPanel implements KeyListener {
        private static final int CHECK_GAP = 10;
        private static final int CHECK_ARC = 20;
        private static final int CHECK_SIZE = 86;
        private Check[][] checks = new Check[4][4];
        private boolean isAdd = true;

        public GameBoard() {
            initGame();
            addKeyListener(this);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    initGame();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_UP:
                    moveUp();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_DOWN:
                    moveDown();
                    createCheck();
                    judgeGameOver();
                    break;
                default:
                    break;
            }
            repaint();
        }

        private void initGame() {
            score = 0;
            for (int indexRow = 0; indexRow < 4; indexRow++) {
                for (int indexCol = 0; indexCol < 4; indexCol++) {
                    checks[indexRow][indexCol] = new Check();
                }
            }
            isAdd = true;
            createCheck();
            isAdd = true;
            createCheck();
        }

        private void createCheck() {
            List<Check> list = getEmptyChecks();
            if (!list.isEmpty() && isAdd) {
                Random random = new Random();
                int index = random.nextInt(list.size());
                Check check = list.get(index);
                // 2, 4出现概率3:1
                check.value = (random.nextInt(4) % 3 == 0) ? 2 : 4;
                isAdd = false;
            }
        }

        // 获取空白方格
        private List<Check> getEmptyChecks() {
            List<Check> checkList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (checks[i][j].value == 0) {
                        checkList.add(checks[i][j]);
                    }
                }
            }
            return checkList;
        }

        private boolean judgeGameOver() {
            jLblScore.setText(score + "");
            if (!getEmptyChecks().isEmpty()) {
                return false;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (checks[i][j].value == checks[i][j + 1].value
                            || checks[i][j].value == checks[i + 1][j].value) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean moveLeft() {
            for (int i = 0; i < 4; i++) {
                for (int j = 1, index = 0; j < 4; j++) {
                    if (checks[i][j].value > 0) {
                        if (checks[i][j].value == checks[i][index].value) {
                            score += checks[i][index++].value <<= 1;
                            checks[i][j].value = 0;
                            isAdd = true;
                        } else if (checks[i][index].value == 0) {
                            checks[i][index].value = checks[i][j].value;
                            checks[i][j].value = 0;
                            isAdd = true;
                        } else if (checks[i][++index].value == 0) {
                            checks[i][index].value = checks[i][j].value;
                            checks[i][j].value = 0;
                            isAdd = true;
                        }
                    }
                }
            }
            return isAdd;
        }

        private boolean moveRight() {
            for (int i = 0; i < 4; i++) {
                for (int j = 2, index = 3; j >= 0; j--) {
                    if (checks[i][j].value > 0) {
                        if (checks[i][j].value == checks[i][index].value) {
                            score += checks[i][index--].value <<= 1;
                            checks[i][j].value = 0;
                            isAdd = true;
                        } else if (checks[i][index].value == 0) {
                            checks[i][index].value = checks[i][j].value;
                            checks[i][j].value = 0;
                            isAdd = true;
                        } else if (checks[i][--index].value == 0) {
                            checks[i][index].value = checks[i][j].value;
                            checks[i][j].value = 0;
                            isAdd = true;
                        }
                    }
                }
            }
            return isAdd;
        }

        private boolean moveUp() {
            for (int i = 0; i < 4; i++) {
                for (int j = 1, index = 0; j < 4; j++) {
                    if (checks[j][i].value > 0) {
                        if (checks[j][i].value == checks[index][i].value) {
                            score += checks[index++][i].value <<= 1;
                            checks[j][i].value = 0;
                            isAdd = true;
                        } else if (checks[index][i].value == 0) {
                            checks[index][i].value = checks[j][i].value;
                            checks[j][i].value = 0;
                            isAdd = true;
                        } else if (checks[++index][i].value == 0) {
                            checks[index][i].value = checks[j][i].value;
                            checks[j][i].value = 0;
                            isAdd = true;
                        }
                    }
                }
            }
            return isAdd;
        }

        private boolean moveDown() {
            for (int i = 0; i < 4; i++) {
                for (int j = 2, index = 3; j >= 0; j--) {
                    if (checks[j][i].value > 0) {
                        if (checks[j][i].value == checks[index][i].value) {
                            score += checks[index--][i].value <<= 1;
                            checks[j][i].value = 0;
                            isAdd = true;
                        } else if (checks[index][i].value == 0) {
                            checks[index][i].value = checks[j][i].value;
                            checks[j][i].value = 0;
                            isAdd = true;
                        } else if (checks[--index][i].value == 0) {
                            checks[index][i].value = checks[j][i].value;
                            checks[j][i].value = 0;
                            isAdd = true;
                        }
                    }
                }
            }
            return isAdd;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    drawCheck(g, i, j);
                }
            }
            // GameOver
            if (judgeGameOver()) {
                g.setColor(new Color(64, 64, 64, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.setFont(topicFont);
                FontMetrics fms = getFontMetrics(topicFont);
                String value = "Game Over!";
                g.drawString(value,
                        (getWidth() - fms.stringWidth(value)) / 2,
                        getHeight() / 2);
                int score = Integer.parseInt(jLblScore.getText());
                if (score >= 1024) {
                    JPanel jp = new JPanel(new GridLayout(3,1,0,5));
                    jp.add(new JLabel("Play Again ?"),JLabel.CENTER);
                    jp.add(new JLabel("Your Stamp++ !!"),JLabel.CENTER);
                    jp.add(new JLabel("Congratulation ! "),JLabel.CENTER);

                    int result = JOptionPane.showConfirmDialog(
                            jFrameMain,
                            jp,
                            "Notice",
                            JOptionPane.YES_NO_OPTION
                    );

                    CoffeeShopSystem.user.getRegistered(LogInMenu.name).getCard().addStamp();

                    if (result == 0){
                        jFrameMain.dispose();
                        new G2048GameView().showView();
                    }else {
                        jFrameMain.dispose();
                    }
                }else {
                    JPanel jp = new JPanel();
                    jp.add(new JLabel("Get another %d points and you'll get stamp!".formatted(1024-score)));
                    jp.add(new JLabel("Try Again ?"));
                    int result = JOptionPane.showConfirmDialog(
                            jFrameMain,
                            jp,
                            "Notice",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (result == 0){
//                        System.out.println("again");
                        jFrameMain.dispose();
                        new G2048GameView().showView();
                    }else {
                        jFrameMain.dispose();
                    }
                }
            }
        }

        private void drawCheck(Graphics g, int i, int j) {
            Graphics2D gg = (Graphics2D) g;
            gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                    RenderingHints.VALUE_STROKE_NORMALIZE);
            Check check = checks[i][j];
            gg.setColor(check.getBackground());
            gg.fillRoundRect(CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * j,
                    CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * i,
                    CHECK_SIZE, CHECK_SIZE, CHECK_ARC, CHECK_ARC);
            gg.setColor(check.getForeground());
            gg.setFont(check.getCheckFont());
            FontMetrics fms = getFontMetrics(check.getCheckFont());
            String value = String.valueOf(check.value);
            gg.drawString(value,
                    CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * j +
                            (CHECK_SIZE - (fms.stringWidth(value))) / 2,
                    CHECK_GAP + (CHECK_GAP + CHECK_SIZE) * i +
                            (CHECK_SIZE - (fms.getAscent() + fms.getDescent())) / 2
                            + fms.getAscent());
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    @Override
    public void showView() {
        jFrameMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrameMain.setVisible(true);
    }
}
  
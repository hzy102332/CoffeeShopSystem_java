package CoffeeShop;

import CoffeeShop.order.BuyAndReceipts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegisterMenu {

    private Registered registered;
    public static void Page(Registered registered){

        JFrame jf=new JFrame("Registered Customer Menu");
        jf.setSize(new Dimension(600, 600));
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(new GridLayout(5,1,0,20));
        jf.setResizable(false);

        JLabel jl=new JLabel("Registered Customer Menu",JLabel.CENTER);
        jl.setFont(new Font("TimesRoman", Font.BOLD, 34));
        jf.add(jl);

        JPanel stampinfo = new JPanel(null);
        stampinfo.setPreferredSize(new Dimension(70,300));
        stampinfo.setBackground(Color.YELLOW);

        JLabel stamp;
        if (registered.getCard().FreeDrink()){
            stamp = new JLabel(String.format("You have %d stamps on your card\n You can get a free drink !",
                    registered.getStampNum()),JLabel.CENTER);
            stamp.setFont(new Font("TimesRoman", Font.BOLD, 14));
            stamp.setBounds(100,0,400,40);
            stamp.setOpaque(true);
            stamp.setBackground(Color.yellow);
            stampinfo.add(stamp);
            JButton sbt = new JButton("Exchange a drink");
//            sbt.setPreferredSize(new Dimension(100,50));
            sbt.setBounds(250,40,150,50);
            sbt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    registered.getCard().getFreeDrink();
                    JOptionPane.showConfirmDialog(
                            jf,
                            "You have exchanged a drink !!",
                            "Notice",
                            JOptionPane.CLOSED_OPTION
                    );
                    stampinfo.removeAll();
                    JLabel stamp1 = new JLabel(String.format("Number of stamps on card: %d",
                            registered.getStampNum()), JLabel.CENTER);
                    stamp1.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 14));
                    stamp1.setBounds(150,0,300,50);
                    stampinfo.add(stamp1);
//                    stampinfo.setBackground(null);
                    stampinfo.updateUI();
                }
            });
            stampinfo.add(sbt);

        }else {
            stamp = new JLabel(String.format("Number of stamps on card: %d",
                    registered.getStampNum()), JLabel.CENTER);
            stamp.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 14));
            stamp.setBounds(150,0,300,50);
            stampinfo.add(stamp);
            JButton check = new JButton("Refresh");
            check.setBounds(250,40,100,50);
            check.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    stamp.setText(String.format("Number of stamps on card: %d",
                            registered.getStampNum()));
                }
            });
            stampinfo.add(check);
        }

        jf.add(stampinfo);

        JPanel vip = new JPanel();
        JLabel j = new JLabel("Welcome back VIP !");
        j.setFont(new Font("TimesRoman", Font.BOLD, 20));
        j.setOpaque(true);
        j.setForeground(Color.magenta);

        if (registered.isVipStatus()){
            vip.add(j);
        }else {
            JButton join = new JButton("Join VIP scheme!");
            join.setPreferredSize(new Dimension(150,50));
            join.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int result = JOptionPane.showConfirmDialog(
                            jf,                            "Join in VIP group",
                            "Confirm dialog",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (result == 0){
                        registered.setVipStatus(true);
                        JOptionPane.showMessageDialog(
                                jf,
                                "Welcome to our membership!"
                        );
                        vip.removeAll();
                        vip.add(j);
                        vip.updateUI();
                    }
                }
            });
            vip.add(join);
        }

        jf.add(vip);

        JPanel backAndbuy = new JPanel(new FlowLayout());
        jf.add(backAndbuy);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.dispose();
                new LogInMenu(CoffeeShopSystem.user);
            }
        });
        JButton buy = new JButton("Buy Something");
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.dispose();
                new BuyAndReceipts().setVisible(true);
            }
        });
        JButton book_room = new JButton("Book Private Room");
        book_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                jf.dispose();
                new BookroomUI().setVisible(true);
            }
        });
        back.setPreferredSize(new Dimension(100,40));
        buy.setPreferredSize(new Dimension(150,60));
        book_room.setPreferredSize(new Dimension(150,60));
        backAndbuy.add(buy);
        backAndbuy.add(book_room);
        backAndbuy.add(back);

        JPanel unre = new JPanel(new FlowLayout());
        jf.add(unre);

        JButton unregister = new JButton("Unregister >>>");
        unregister.setPreferredSize(new Dimension(130,30));
        unregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int result = JOptionPane.showConfirmDialog(
                        jf,
                        "I am confirm to unregister !!!!",
                        "Warning",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                System.out.println(result);
                if (result == 0){
                    CoffeeShopSystem.user.deleteUser(registered.getName());
                    jf.dispose();
                    new LogInMenu(CoffeeShopSystem.user);
                }
            }
        });
        JButton history = new JButton("Check Receipts");
        history.setPreferredSize(new Dimension(140,50));
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ImageIcon icon = new ImageIcon("src/CoffeeShop/images/receipt.jpeg");
                icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(
                        jf,
                        registered.getOrder().equals("") ? "There is no Receipt history !" : registered.getOrder(),
                        "Receipt Info",
                        JOptionPane.CLOSED_OPTION,
                        icon
                );
            }
        });

        JButton game = new JButton("Game !");
        game.setFont(new Font("Times Roman", Font.BOLD, 15));
        game.setBackground(new Color(248, 21, 21));
        game.setOpaque(false);
        game.setPreferredSize(new Dimension(100,80));
        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Gamecenter().Gamecenter();
            }
        });
        unre.add(unregister);
        unre.add(history);
        unre.add(game);

        jf.setVisible(true);
    }

//    public static void main(String[] args) {
//        Page();
//    }
}

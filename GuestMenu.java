package CoffeeShop;

import CoffeeShop.order.BuyAndReceipts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GuestMenu {
    public static void Page(User user) {
        JFrame jf = new JFrame("GuestMenu Menu");
        // 1.设置窗体大小和标题
        jf.setSize(new Dimension(600, 600));
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        // 最精准的布局模式空布局
        jf.setLayout(new GridLayout(4,1,10,0));

        JLabel jl = new JLabel("GuestMenu Menu", JLabel.CENTER);
//        jl.setPreferredSize(new Dimension(600, 0));
        jl.setFont(new Font("TimesRoman", Font.BOLD, 24));
        jf.add(jl);

        JLabel sen = new JLabel("Do you want to RegisterMenu ?",JLabel.CENTER);
        sen.setFont(new Font("TimesRoman", Font.BOLD, 20));
        jf.add(sen);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(1,20,0));
        jf.add(jPanel);

        JButton register = new JButton("Register as member!");
        register.setPreferredSize(new Dimension(200,50));
        JButton purchase = new JButton("Just Buy Something");
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.dispose();
                new BuyAndReceipts().setVisible(true);
            }
        });
        purchase.setPreferredSize(new Dimension(200,50));

        int IDnumber = Integer.parseInt(String.valueOf(System.currentTimeMillis()%100000));
        JPanel myPanel = new JPanel(new GridLayout(4,1,0,0));
        myPanel.add(new JLabel("Your ID number:"));
        myPanel.add(new JLabel(String.format("    %d",IDnumber)));
        myPanel.add(new JLabel("Please enter your name:"));


        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String)JOptionPane.showInputDialog(
                        jf,
                        myPanel,
                        "Your registeration information",
                        JOptionPane.OK_CANCEL_OPTION
                );
                if (!(result == null)) {
                    if (result.equals("")) {
                        JOptionPane.showMessageDialog(
                                jf,
                                "Registered Failed! \nPlease enter your name!"
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                jf,
                                "Registered successfully!"
                        );
                        jf.dispose();
                        LoyaltyCard loyaltyCard = new LoyaltyCard(result,""+IDnumber,0);
                        Registered registered = new Registered(result,loyaltyCard,false);
                        user.addUser(registered);
                        new RegisterMenu().Page(registered);
                        new news().news();
                    }
                }
            }
        });
        jPanel.add(register);
        jPanel.add(purchase);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.dispose();
                new LogInMenu(CoffeeShopSystem.user);
            }
        });
        JPanel bpanel = new JPanel();
        bpanel.add(back);

        jf.add(bpanel);

        jf.setVisible(true);
    }

//    public static void main(String[] args) {
//        Page();
//    }
}


package CoffeeShop;

import CoffeeShop.games.G2048GameView;
import CoffeeShop.games.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gamecenter {
    public void Gamecenter(){
        JFrame jf = new JFrame("Game Center");
        jf.setSize(new Dimension(450, 500));
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
//        jf.setResizable(false);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setSize(new Dimension(450,500));
        jPanel.setOpaque(false);
        jf.add(jPanel);

        JLabel jl = new JLabel("Welcome to our Game Center !",JLabel.CENTER);
        jl.setBounds(0,0,450,100);
        jl.setFont(new Font("Roman",Font.BOLD, 20));
        jPanel.add(jl);

        ImageIcon icon1 = new ImageIcon("src/CoffeeShop/images/2048.jpeg");
        icon1.setImage(icon1.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));

        ImageIcon icon2 = new ImageIcon("src/CoffeeShop/images/123.png");
        icon2.setImage(icon2.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));

        JLabel i20 = new JLabel(icon1);
        i20.setBounds(50,100,i20.getIcon().getIconWidth(),i20.getIcon().getIconHeight());
        JLabel i12 = new JLabel(icon2);
        i12.setBounds(250,100,i12.getIcon().getIconWidth(),i12.getIcon().getIconHeight());

        jPanel.add(i20);
        jPanel.add(i12);

        JButton b2 = new JButton("Play");
        b2.setBounds(70,270,100,50);
        b2.setBorder(BorderFactory.createBevelBorder(0));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new G2048GameView().showView();
            }
        });
        jPanel.add(b2);
        JButton b1 = new JButton("Play");
        b1.setBounds(270,270,100,50);
        b1.setBorder(BorderFactory.createBevelBorder(0));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new GameFrame();
                    }
                });
            }
        });
        jPanel.add(b1);


        jf.setVisible(true);
    }

}

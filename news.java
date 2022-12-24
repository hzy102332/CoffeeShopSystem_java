package CoffeeShop;

import CoffeeShop.games.G2048GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class news {
    public void news(){
        JFrame jf = new JFrame("News");
        jf.setSize(new Dimension(415, 400));
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLayout(null);
        jf.setResizable(false);

        JPanel jp1 = new JPanel();
        jp1.setBounds(0, 0, 415, 400);
        jp1.setLayout(null);
        jp1.setVisible(true);

        JScrollPane js = new JScrollPane();
        js.setBounds(0,0,400,400);

        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.setPreferredSize(new Dimension(370,750));
        jPanel.setBackground(Color.red);
        jPanel.setLayout(null);

        js.getViewport().add(jPanel);
        jp1.add(js);
        jf.add(jp1);

        ImageIcon icon1 = new ImageIcon("src/CoffeeShop/images/coffee.png");
        icon1.setImage(icon1.getImage().getScaledInstance(400,600, Image.SCALE_DEFAULT));

        ImageIcon nicon = new ImageIcon("src/CoffeeShop/images/2048.jpeg");
        nicon.setImage(nicon.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT));

        JLabel newp = new JLabel(icon1);
        newp.setBounds(0,80,400,600);
        JLabel jLabel = new JLabel(nicon);
        jLabel.setBounds(10,10,60,60);
        jPanel.add(jLabel);
        jPanel.add(newp);

        JLabel text = new JLabel("Activity Today!");
        text.setFont(new Font("Times Roman", Font.BOLD, 15));
        text.setBounds(120,40,200,30);
        text.setForeground(Color.red);
        jPanel.add(text);
        JLabel text1 = new JLabel("《=Get 1024 points to get a stamp!");
        text1.setFont(new Font("Times Roman", Font.BOLD, 15));
        text1.setBounds(80,5,300,30);
        jPanel.add(text1);

        JButton bt = new JButton("Go");
        bt.setBounds(280,30,50,50);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.dispose();
                new G2048GameView().showView();
            }
        });

        jPanel.add(bt);
        jf.setVisible(true);
    }
}

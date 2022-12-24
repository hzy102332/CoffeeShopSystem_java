package CoffeeShop;

import CoffeeShop.order.AdminManagementSystem;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LogInMenu extends JFrame implements ActionListener {

    public static String name;
    JLabel coffee = new JLabel("Coffee shop", JLabel.CENTER);
    JLabel username = new JLabel("Username:");
    //    JLabel l3 = new JLabel("Password:");
    /* 控制Label中的字体风格 */
    Font f = new Font("TimesRoman", Font.BOLD, 34);
    Font f2 = new Font("TimesRoman", Font.BOLD, 15);
    /* 下拉单 */
    JComboBox jc1 = new JComboBox();
    /* 按钮 */
    JButton bt1 = new JButton("Login");
    JButton bt2 = new JButton("Guest Login");
    /* 文本框 */
    JTextArea textarea;
    JTextField userinput = new JTextField();

    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public LogInMenu(User user) {
        coffee.setFont(f);        //字体风格Font参数和Label控件 名称为coffee的对象 绑定。
        username.setFont(f2);
        coffee.setBounds(new Rectangle(200, 20, 200, 40));
        username.setBounds(new Rectangle(190, 160, 200, 40));
        bt1.setBounds(new Rectangle(140, 380, 120, 40));
        bt2.setBounds(new Rectangle(320, 380, 120, 40));
        userinput.setBounds(new Rectangle(290, 170, 180, 26));
        jc1.setBounds(new Rectangle(200, 230, 200, 30));

        /* 给对象名为jc1的Combobox控件，添加内容。 */
        jc1.addItem("Registered customer");
        jc1.addItem("Admin");

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = userinput.getText(); // 获取文本框内的用户名
                String kind = jc1.getSelectedItem().toString();
                if (0 == name.length()) {
                    JOptionPane.showMessageDialog(null, "Please enter your ACCOUNT!");
                    userinput.grabFocus();
                }else{
                    if (kind == "Admin") {
                        if (user.CheckAdminUser(name)) {
                            JOptionPane.showMessageDialog(null, "Log in successfully!");
//                            dispose();
                            userinput.setText("");
                            new AdminManagementSystem().setVisible(true);
                        } else
                            JOptionPane.showMessageDialog(null, "Wrong account!");
                    } else if (kind.equals("Registered customer")) {
                        if (user.CheckRegisteredUser(name)) {
                            JOptionPane.showMessageDialog(null, "Log in successfully!");
                            dispose();
                            userinput.setText("");
                            CoffeeShopSystem.isregister = true;
                            new RegisterMenu().Page(user.getRegistered(name));
                            new news().news();
                        } else
                            JOptionPane.showMessageDialog(null, "Wrong account!");
                    }
                }
            }
        });

        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LogInMenu.this.dispose();
                CoffeeShopSystem.isregister = false;
                new GuestMenu().Page(user);
            }
        });

        this.setTitle("LogIn");        //设置窗体标题名称
        this.setLayout(null); // 窗体布局设置为自定义，这行代码不可更换到其他位置//
        this.setSize(600, 600);
        this.add(bt2);
        this.add(bt1);
        this.add(coffee);
        this.add(username);
        this.add(userinput);
        this.add(jc1);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);                    //窗体可以看见，如果是false，那么运行程序后窗体看不见，。
        this.setResizable(false);                //setResizable用于控制窗体大小可拉伸与否。false表示，窗体大小定死了。
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}


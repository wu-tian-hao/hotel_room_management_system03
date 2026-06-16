package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private RoomManageFrame roomFrame;
    private OrderManageFrame orderFrame;
    private CustomerManageFrame customerFrame;

    public MainFrame() {

        setTitle("酒店管理系统");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton roomBtn = new JButton("房间管理");
        JButton orderBtn = new JButton("订单管理");
        JButton customerBtn = new JButton("客户管理");
        JButton exitBtn = new JButton("退出系统");

        panel.add(roomBtn);
        panel.add(orderBtn);
        panel.add(customerBtn);
        panel.add(exitBtn);

        add(panel);

        // ================= 房间管理 =================
        roomBtn.addActionListener(e -> {

            if (roomFrame == null || !roomFrame.isVisible()) {
                roomFrame = new RoomManageFrame();
                roomFrame.setVisible(true);
            } else {
                roomFrame.toFront();
            }
        });

        // ================= 订单管理 =================
        orderBtn.addActionListener(e -> {

            if (orderFrame == null || !orderFrame.isVisible()) {
                orderFrame = new OrderManageFrame();
                orderFrame.setVisible(true);
            } else {
                orderFrame.toFront();
            }
        });

        // ================= 客户管理 =================
        customerBtn.addActionListener(e -> {

            if (customerFrame == null || !customerFrame.isVisible()) {
                customerFrame = new CustomerManageFrame();
                customerFrame.setVisible(true);
            } else {
                customerFrame.toFront();
            }
        });

        // ================= 退出系统 =================
        exitBtn.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
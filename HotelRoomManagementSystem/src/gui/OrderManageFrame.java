package gui;

import model.Customer;
import model.Order;
import service.CustomerService;
import service.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderManageFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JComboBox<Customer> customerBox;

    private OrderService orderService = new OrderService();
    private CustomerService customerService = new CustomerService();

    public OrderManageFrame() {

        setTitle("订单管理");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        loadData();
    }

    private void initUI() {

        model = new DefaultTableModel();

        model.addColumn("订单ID");
        model.addColumn("客户ID");
        model.addColumn("房间ID");
        model.addColumn("入住时间");
        model.addColumn("离店时间");
        model.addColumn("总价");
        model.addColumn("状态");

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ================= 下拉框 =================
        customerBox = new JComboBox<>();

        List<Customer> customers = customerService.getAllCustomers();

        if (customers != null && !customers.isEmpty()) {
            for (Customer c : customers) {
                customerBox.addItem(c);
            }
        } else {
            System.out.println("❌ 客户列表为空");
        }

        JPanel panel = new JPanel();

        JButton addBtn = new JButton("新增订单");
        JButton deleteBtn = new JButton("删除订单");
        JButton refreshBtn = new JButton("刷新");

        panel.add(new JLabel("选择客户"));
        panel.add(customerBox);

        panel.add(addBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        add(panel, BorderLayout.SOUTH);

        // ================= 新增订单 =================
        addBtn.addActionListener(e -> {

            try {
                Customer c = (Customer) customerBox.getSelectedItem();

                if (c == null) {
                    JOptionPane.showMessageDialog(this, "请选择客户");
                    return;
                }

                int customerId = c.getCustomerId();

                String roomIdStr = JOptionPane.showInputDialog("房间ID");
                String inStr = JOptionPane.showInputDialog("入住日期 yyyy-MM-dd");
                String outStr = JOptionPane.showInputDialog("离店日期 yyyy-MM-dd");

                if (roomIdStr == null || inStr == null || outStr == null) {
                    JOptionPane.showMessageDialog(this, "输入不能为空");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Order order = new Order();
                order.setCustomerId(customerId);
                order.setRoomId(Integer.parseInt(roomIdStr));
                order.setCheckInDate(sdf.parse(inStr));
                order.setCheckOutDate(sdf.parse(outStr));
                order.setStatus("已预订");

                int result = orderService.addOrder(order);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "添加成功");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "添加失败");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "系统错误，请检查输入");
            }
        });

        // ================= 删除 =================
        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "请选择订单");
                return;
            }

            int orderId = (int) model.getValueAt(row, 0);

            orderService.deleteOrder(orderId);

            loadData();
        });

        // ================= 刷新 =================
        refreshBtn.addActionListener(e -> loadData());
    }

    // ================= 加载数据 =================
    private void loadData() {

        model.setRowCount(0);

        List<Order> list = orderService.getAllOrders();

        if (list != null) {
            for (Order o : list) {

                model.addRow(new Object[]{
                        o.getOrderId(),
                        o.getCustomerId(),
                        o.getRoomId(),
                        o.getCheckInDate(),
                        o.getCheckOutDate(),
                        o.getTotalPrice(),
                        o.getStatus()
                });
            }
        }

        System.out.println("✔ 订单加载完成");
    }

    public static void main(String[] args) {
        new OrderManageFrame().setVisible(true);
    }
}
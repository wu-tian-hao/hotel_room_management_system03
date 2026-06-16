package gui;

import model.Customer;
import service.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerManageFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private CustomerService customerService = new CustomerService();

    public CustomerManageFrame() {

        setTitle("客户管理");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        loadData();
    }

    private void initUI() {

        model = new DefaultTableModel();

        model.addColumn("客户ID");
        model.addColumn("姓名");

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton addBtn = new JButton("新增客户");
        JButton deleteBtn = new JButton("删除客户");
        JButton refreshBtn = new JButton("刷新");

        panel.add(addBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        add(panel, BorderLayout.SOUTH);

        // ================= 新增客户 =================
        addBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("请输入客户姓名");

            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "姓名不能为空");
                return;
            }

            Customer c = new Customer();
            c.setName(name);

            customerService.addCustomer(c);

            JOptionPane.showMessageDialog(this, "添加成功");

            loadData();
        });

        // ================= 删除客户 =================
        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "请选择客户");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            customerService.deleteCustomer(id);

            loadData();
        });

        // ================= 刷新 =================
        refreshBtn.addActionListener(e -> loadData());
    }

    // ================= 加载数据 =================
    private void loadData() {

        model.setRowCount(0);

        List<Customer> list = customerService.getAllCustomers();

        if (list != null) {
            for (Customer c : list) {
                model.addRow(new Object[]{
                        c.getCustomerId(),
                        c.getName()
                });
            }
        }
    }

    public static void main(String[] args) {
        new CustomerManageFrame().setVisible(true);
    }
}
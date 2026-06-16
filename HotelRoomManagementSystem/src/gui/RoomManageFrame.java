package gui;

import model.Room;
import service.RoomService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomManageFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private RoomService roomService = new RoomService();

    public RoomManageFrame() {

        setTitle("房间管理");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        loadData();
    }

    // ================= UI =================
    private void initUI() {

        model = new DefaultTableModel();

        model.addColumn("房间ID");
        model.addColumn("房间号");
        model.addColumn("类型");
        model.addColumn("价格");
        model.addColumn("状态");

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton addBtn = new JButton("新增房间");
        JButton deleteBtn = new JButton("删除房间");
        JButton refreshBtn = new JButton("刷新");

        panel.add(addBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        add(panel, BorderLayout.SOUTH);

        // ================= 新增 =================
        addBtn.addActionListener(e -> {

            try {
                String number = JOptionPane.showInputDialog("房间号");
                String type = JOptionPane.showInputDialog("房间类型");
                String priceStr = JOptionPane.showInputDialog("价格");

                if (number == null || type == null || priceStr == null ||
                        number.trim().isEmpty() || type.trim().isEmpty() || priceStr.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(this, "不能为空");
                    return;
                }

                double price = Double.parseDouble(priceStr);

                Room room = new Room();
                room.setRoomNumber(number);
                room.setRoomType(type);
                room.setPrice(price);
                room.setStatus("空闲");

                roomService.addRoom(room);

                JOptionPane.showMessageDialog(this, "添加成功");

                loadData();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "价格必须是数字");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "系统错误");
            }
        });

        // ================= 删除 =================
        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "请选择房间");
                return;
            }

            int roomId = (int) model.getValueAt(row, 0);

            roomService.deleteRoom(roomId);

            JOptionPane.showMessageDialog(this, "删除成功");

            loadData();
        });

        // ================= 刷新 =================
        refreshBtn.addActionListener(e -> loadData());
    }

    // ================= 数据加载 =================
    private void loadData() {

        model.setRowCount(0);

        List<Room> list = roomService.getAllRooms();

        if (list == null) return;

        for (Room r : list) {

            model.addRow(new Object[]{
                    r.getRoomId(),
                    r.getRoomNumber(),
                    r.getRoomType(),
                    r.getPrice(),
                    r.getStatus()
            });
        }
    }

    public static void main(String[] args) {
        new RoomManageFrame().setVisible(true);
    }
}
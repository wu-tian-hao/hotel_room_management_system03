package dao;

import database.DBUtil;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    // ================= 新增订单 =================
    public int addOrder(Order order) {

        System.out.println("=== 进入 OrderDao.addOrder ===");

        String sql = "INSERT INTO orders(customer_id, room_id, check_in_date, check_out_date, total_price, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (order.getCheckInDate() == null || order.getCheckOutDate() == null) {
                System.out.println("❌ 日期不能为空");
                return 0;
            }

            ps.setInt(1, order.getCustomerId());
            ps.setInt(2, order.getRoomId());
            ps.setDate(3, new java.sql.Date(order.getCheckInDate().getTime()));
            ps.setDate(4, new java.sql.Date(order.getCheckOutDate().getTime()));
            ps.setDouble(5, order.getTotalPrice());
            ps.setString(6, order.getStatus());

            int rows = ps.executeUpdate();

            System.out.println("✔ 插入成功，影响行数 = " + rows);

            return rows;

        } catch (SQLException e) {
            System.out.println("❌ 添加订单失败");
            e.printStackTrace();
        }

        return 0;
    }

    // ================= 删除订单 =================
    public int deleteOrder(int orderId) {

        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            int rows = ps.executeUpdate();

            System.out.println("✔ 删除成功，影响行数 = " + rows);

            return rows;

        } catch (SQLException e) {
            System.out.println("❌ 删除订单失败");
            e.printStackTrace();
        }

        return 0;
    }

    // ================= 查询所有订单 =================
    public List<Order> getAllOrders() {

        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerId(rs.getInt("customer_id"));
                o.setRoomId(rs.getInt("room_id"));
                o.setCheckInDate(rs.getDate("check_in_date"));
                o.setCheckOutDate(rs.getDate("check_out_date"));
                o.setTotalPrice(rs.getDouble("total_price"));
                o.setStatus(rs.getString("status"));

                list.add(o);
            }

            System.out.println("✔ 查询订单成功，数量 = " + list.size());

        } catch (SQLException e) {
            System.out.println("❌ 查询订单失败");
            e.printStackTrace();
        }

        return list;
    }

    // ================= 根据ID查询（可选）=================
    public Order getById(int orderId) {

        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerId(rs.getInt("customer_id"));
                o.setRoomId(rs.getInt("room_id"));
                o.setCheckInDate(rs.getDate("check_in_date"));
                o.setCheckOutDate(rs.getDate("check_out_date"));
                o.setTotalPrice(rs.getDouble("total_price"));
                o.setStatus(rs.getString("status"));
                return o;
            }

        } catch (SQLException e) {
            System.out.println("❌ 查询订单失败");
            e.printStackTrace();
        }

        return null;
    }
}
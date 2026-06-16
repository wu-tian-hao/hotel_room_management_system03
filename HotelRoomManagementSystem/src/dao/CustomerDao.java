package dao;

import database.DBUtil;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    // ================= 查询所有客户 =================
    public List<Customer> getAllCustomers() {

        List<Customer> list = new ArrayList<>();

        String sql = "SELECT * FROM customer";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Customer c = new Customer();

                c.setCustomerId(rs.getInt("customer_id"));
                c.setName(rs.getString("name"));

                list.add(c);
            }

            System.out.println("✔ 查询客户成功，数量 = " + list.size());

        } catch (SQLException e) {
            System.out.println("❌ 查询客户失败");
            e.printStackTrace();
        }

        return list;
    }

    // ================= 根据ID查询客户 =================
    public Customer getById(int id) {

        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Customer c = new Customer();

                c.setCustomerId(rs.getInt("customer_id"));
                c.setName(rs.getString("name"));

                return c;
            }

        } catch (SQLException e) {
            System.out.println("❌ 根据ID查询客户失败");
            e.printStackTrace();
        }

        return null;
    }

    // ================= 新增客户（你刚刚报错就是缺这个） =================
    public int addCustomer(Customer customer) {

        String sql = "INSERT INTO customer(name) VALUES (?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getName());

            int rows = ps.executeUpdate();

            System.out.println("✔ 新增客户成功，影响行数 = " + rows);

            return rows;

        } catch (SQLException e) {
            System.out.println("❌ 新增客户失败");
            e.printStackTrace();
        }

        return 0;
    }

    // ================= 删除客户（备用） =================
    public int deleteCustomer(int id) {

        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            System.out.println("✔ 删除客户成功，影响行数 = " + rows);

            return rows;

        } catch (SQLException e) {
            System.out.println("❌ 删除客户失败");
            e.printStackTrace();
        }

        return 0;
    }
}
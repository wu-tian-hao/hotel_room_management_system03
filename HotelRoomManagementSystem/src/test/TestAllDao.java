package test;

import dao.CustomerDao;
import dao.UserDao;
import dao.RoomDao;
import dao.OrderDao;
import model.Customer;
import model.User;
import model.Room;
import model.Order;

import java.text.SimpleDateFormat;

public class TestAllDao {

    public static void main(String[] args) throws Exception {

        CustomerDao customerDao = new CustomerDao();
        UserDao userDao = new UserDao();
        RoomDao roomDao = new RoomDao();
        OrderDao orderDao = new OrderDao();

        System.out.println("=== UserDao 测试 ===");
        User u = new User(0, "admin", "123456");
        userDao.addUser(u);

        System.out.println("=== CustomerDao 测试 ===");
        Customer c = new Customer(0, "张三", "13800000000", "1234567890");
        customerDao.addCustomer(c);

        System.out.println("=== RoomDao 测试 ===");
        Room r = new Room(0, "101", "单人", 200.0, "空闲");
        roomDao.addRoom(r);

        System.out.println("=== OrderDao 测试 ===");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Order o = new Order(0, 1, 1, sdf.parse("2026-06-11"), sdf.parse("2026-06-12"), 200.0, "已入住");
        orderDao.addOrder(o);

        System.out.println("=== 全部插入完成 ===");
    }
}
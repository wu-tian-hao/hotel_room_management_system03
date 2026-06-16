package service;

import dao.OrderDao;
import dao.RoomDao;
import model.Order;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderService {

    private OrderDao orderDao = new OrderDao();
    private RoomDao roomDao = new RoomDao();

    // ================= 新增订单（核心：自动算价格）=================
    public int addOrder(Order order) {

        // ❗防空（很重要）
        if (order.getCheckInDate() == null || order.getCheckOutDate() == null) {
            System.out.println("❌ 日期不能为空");
            return 0;
        }

        // ================= 1. 获取房间单价 =================
        double price = roomDao.getPriceById(order.getRoomId());

        if (price <= 0) {
            System.out.println("❌ 房间价格获取失败");
            return 0;
        }

        // ================= 2. 计算天数 =================
        long diff = order.getCheckOutDate().getTime()
                - order.getCheckInDate().getTime();

        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if (days <= 0) {
            days = 1; // 最少一天
        }

        // ================= 3. 自动计算总价 =================
        double total = price * days;

        order.setTotalPrice(total);

        System.out.println("✔ 自动计算价格成功：");
        System.out.println("单价 = " + price);
        System.out.println("天数 = " + days);
        System.out.println("总价 = " + total);

        // ================= 4. 调用DAO保存 =================
        return orderDao.addOrder(order);
    }

    // ================= 删除订单 =================
    public int deleteOrder(int orderId) {
        return orderDao.deleteOrder(orderId);
    }

    // ================= 查询所有订单 =================
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
package dao;

import model.Room;
import database.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    // =========================
    // 查询所有房间
    // =========================
    public List<Room> getAllRooms() throws Exception {

        List<Room> list = new ArrayList<>();

        String sql = "SELECT * FROM room";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Room room = new Room();

                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setRoomType(rs.getString("room_type"));
                room.setPrice(rs.getDouble("price"));
                room.setStatus(rs.getString("status"));

                list.add(room);
            }
        }

        return list;
    }

    public double getPriceById(int roomId) {

        String sql = "SELECT price FROM room WHERE room_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // =========================
    // 新增房间
    // =========================
    public void addRoom(Room room) throws Exception {

        String sql = "INSERT INTO room(room_number, room_type, price, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, room.getRoomNumber());
            ps.setString(2, room.getRoomType());
            ps.setDouble(3, room.getPrice());
            ps.setString(4, room.getStatus());

            ps.executeUpdate();
        }
    }
    public Room getRoomById(int roomId) throws Exception {

        String sql = "SELECT * FROM room WHERE room_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Room room = new Room();

                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setRoomType(rs.getString("room_type"));
                room.setPrice(rs.getDouble("price"));
                room.setStatus(rs.getString("status"));

                return room;
            }
        }

        return null;
    }

    // =========================
    // 删除房间
    // =========================
    public void deleteRoom(int roomId) throws Exception {

        String sql = "DELETE FROM room WHERE room_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            ps.executeUpdate();
        }
    }

    // =========================
    // 修改房间状态（后面会用）
    // =========================
    public void updateRoomStatus(int roomId, String status) throws Exception {

        String sql = "UPDATE room SET status = ? WHERE room_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, roomId);

            ps.executeUpdate();
        }
    }
}
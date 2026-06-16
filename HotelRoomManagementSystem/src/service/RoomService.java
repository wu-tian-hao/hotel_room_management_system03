package service;

import dao.RoomDao;
import model.Room;

import java.util.List;

public class RoomService {

    private RoomDao roomDao = new RoomDao();

    // 查询所有房间
    public List<Room> getAllRooms() {
        try {
            return roomDao.getAllRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ⭐新增房间（你缺的就是这个）
    public void addRoom(Room room) {
        try {
            roomDao.addRoom(room);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ⭐删除房间（后面要用）
    public void deleteRoom(int roomId) {
        try {
            roomDao.deleteRoom(roomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
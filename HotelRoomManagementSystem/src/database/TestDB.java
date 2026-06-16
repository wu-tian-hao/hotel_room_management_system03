package database;

import java.sql.Connection;

public class TestDB {

    public static void main(String[] args) {

        try {

            Connection conn = DBUtil.getConnection();

            System.out.println("连接成功");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
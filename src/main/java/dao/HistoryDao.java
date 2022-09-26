package dao;

import vo.HistoryVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    void getConnection() {
        String URL = "jdbc:mysql://localhost/kobis_db";
        String id = "root";
        String pass = "1216dltncks!";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, id, pass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int save(Double lat, Double lnt) throws SQLException {


        String SQL = "Insert into history(lat, lnt, date) values(?,?,now())";
        getConnection();
        int cnt = -1;


        try {
            ps = conn.prepareStatement(SQL);
            conn.setAutoCommit(false);
            ps.setDouble(1, lat);
            ps.setDouble(2, lnt);
            cnt = ps.executeUpdate();

            conn.commit();
            return cnt;

        } catch (Exception e) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }

    }

    public int deleteById(int num) throws SQLException {
        String SQL = "Delete from history where id = ?";

        int cnt = -1;
        getConnection();

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, num);
            ps.executeUpdate();
            conn.commit();

            return cnt;
        } catch (SQLException e) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }
    }

    public List<HistoryVo> select() {
        String SQL = "select * from history order by id desc";
        List<HistoryVo> list = new ArrayList<>();
        getConnection();

        try {
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new HistoryVo(
                        rs.getString("id"),
                        rs.getDouble("lat"),
                        rs.getDouble("lnt"),
                        rs.getString("date")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }
    }


    public void DbClose() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

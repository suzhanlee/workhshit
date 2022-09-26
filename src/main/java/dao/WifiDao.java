package dao;

import apidto.RowDto;
import vo.WifiVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {
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

    public void wifiInsert(List<RowDto> list) throws SQLException {

        String SQL = "insert into wifi(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm,\n" +
                "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor,\n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se,\n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door,\n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm)\n" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        getConnection();
        conn.setAutoCommit(false);
        try {
            ps = conn.prepareStatement(SQL);
            int batchSize = 1000;
            int cnt = 0;

            for (int i = 0; i < list.size(); i++) {
                RowDto rowDto = list.get(i);

                ps.setString(1, rowDto.getMgrNo());
                ps.setString(2, rowDto.getWrdofc());
                ps.setString(3, rowDto.getMainNm());
                ps.setString(4, rowDto.getAdres1());
                ps.setString(5, rowDto.getAdres2());
                ps.setString(6, rowDto.getFloor());
                ps.setString(7, rowDto.getTy());
                ps.setString(8, rowDto.getMby());
                ps.setString(9, rowDto.getSvcSe());
                ps.setString(10, rowDto.getCmcwr());
                ps.setString(11, rowDto.getYear());
                ps.setString(12, rowDto.getDoor());
                ps.setString(13, rowDto.getRemars3());
                ps.setString(14, rowDto.getLat());
                ps.setString(15, rowDto.getLnt());
                ps.setString(16, rowDto.getDttm());

                ps.addBatch();
                cnt++;

                if (batchSize == cnt) {
                    ps.executeBatch();
                    ps.clearBatch();
                    cnt = 0;
                }
            }
            ps.executeBatch();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            DbClose();
        }
    }

    public List<WifiVo> search(Double lat, Double lnt) {

        String SQL = "select * " +
                ", format((6371 * acos(cos(radians(" + lat + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + lnt + ")) " +
                "+ sin(radians(" + lat + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi " +
                " order by distance , X_SWIFI_MGR_NO" +
                " limit 20";

        getConnection();
        try {
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            List<WifiVo> list = new ArrayList<>();

            while (rs.next()) {

                list.add(new WifiVo(

                        rs.getString("distance"),
                        rs.getString("X_SWIFI_MGR_NO"),
                        rs.getString("X_SWIFI_WRDOFC"),
                        rs.getString("X_SWIFI_MAIN_NM"),
                        rs.getString("X_SWIFI_ADRES1"),
                        rs.getString("X_SWIFI_ADRES2"),
                        rs.getString("X_SWIFI_INSTL_FLOOR"),
                        rs.getString("X_SWIFI_INSTL_TY"),
                        rs.getString("X_SWIFI_INSTL_MBY"),
                        rs.getString("X_SWIFI_SVC_SE"),
                        rs.getString("X_SWIFI_CMCWR"),
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getString("LAT"),
                        rs.getString("LNT"),
                        rs.getString("WORK_DTTM")

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

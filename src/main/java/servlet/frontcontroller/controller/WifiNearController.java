package servlet.frontcontroller.controller;

import dao.HistoryDao;
import dao.WifiDao;

import servlet.Model;
import vo.WifiVo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class WifiNearController implements Controller {
    WifiDao wifiDao = new WifiDao();
    HistoryDao historyDao = new HistoryDao();

    @Override
    public Model process(HashMap<String, String> paramMap) {


        Double lat = Double.valueOf(paramMap.get("LAT"));
        Double lnt = Double.valueOf(paramMap.get("LNT"));

        List<WifiVo> searchList = wifiDao.search(lat, lnt);
        try {
            historyDao.save(lat, lnt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Model model = new Model("index");

        model.getModel().put("lat", lat);
        model.getModel().put("lnt", lnt);
        model.getModel().put("searchList", searchList);
        return model;

    }
}

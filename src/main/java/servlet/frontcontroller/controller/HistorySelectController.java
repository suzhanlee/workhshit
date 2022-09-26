package servlet.frontcontroller.controller;

import dao.HistoryDao;
import servlet.Model;
import vo.HistoryVo;

import java.util.HashMap;
import java.util.List;

public class HistorySelectController implements Controller {

    private final HistoryDao historyDao;

    public HistorySelectController() {
        this.historyDao = new HistoryDao();
    }


    @Override
    public Model process(HashMap<String, String> paramMap) {

        List<HistoryVo> list = historyDao.select();
        Model model = new Model("history");
        model.getModel().put("list", list);
        model.setViewName("history");

        return model;
    }
}

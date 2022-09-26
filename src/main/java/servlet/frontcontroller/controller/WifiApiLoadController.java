package servlet.frontcontroller.controller;

import apitest.ApiJsonServer;
import servlet.Model;

import java.io.IOException;
import java.util.HashMap;


public class WifiApiLoadController implements Controller {

    ApiJsonServer apiJsonServer = new ApiJsonServer();


    @Override
    public Model process(HashMap<String, String> paramMap) {

        int totalCount = 0;
        try {
            totalCount = apiJsonServer.getWifiJson(0, 1).getTotalcount();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Model model = new Model("wifiLoad");
        model.getModel().put("totalCount", totalCount);

        return model;
    }
}

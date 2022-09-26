package apitest;

import apidto.BaseDto;
import apidto.MainDto;
import com.google.gson.Gson;

public class ApiParser {

    Gson gson = new Gson();

    public MainDto parse(String json) {

        BaseDto baseDto = gson.fromJson(json, BaseDto.class);

        return baseDto.getTbPublicWifiInfo();
    }
}

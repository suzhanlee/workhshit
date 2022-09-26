package apitest;

import apidto.MainDto;

import java.io.IOException;

public class TotalCnt {

    public int getNum() throws IOException {
        ApiJsonServer apiJsonServer = new ApiJsonServer();
        MainDto mainDto = apiJsonServer.getWifiJson(1, 100);
        int totalCount = mainDto.getTotalcount();
        int num = totalCount / 1000;
        if (totalCount > num * 1000) {
            num++;
        }
        return num;
    }
}

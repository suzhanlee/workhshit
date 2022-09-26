package apitest;

import apidto.MainDto;
import apidto.RowDto;

import java.io.IOException;

public class ApiJsonTest {
    public static void main(String[] args) throws IOException {

        ApiJsonServer apiJsonServer = new ApiJsonServer();
        MainDto mainDto = apiJsonServer.getWifiJson(1, 100);

        System.out.println(mainDto.getRowDtos());

        System.out.println("----");

        for (RowDto rowDto : mainDto.getRowDtos()) {
            System.out.println(rowDto.getMainNm());
            System.out.println(rowDto.getDttm());

        }

        System.out.println("----");

        System.out.println(mainDto.getTotalcount());

    }
}

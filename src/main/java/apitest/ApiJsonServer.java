package apitest;

import apidto.MainDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class ApiJsonServer {

    public MainDto getWifiJson(int start, int end) throws IOException {

        String url = "http://openapi.seoul.go.kr:8088/6566634150727564363264436a4d79/json/TbPublicWifiInfo/"
                + start + "/" + end;
        OkHttpClient okHttpClient = new OkHttpClient();

        URL urlRequest = new URL(url);
        Request request = new Request.Builder().url(urlRequest).get().build();

        Response response = okHttpClient.newCall(request).execute();
        String json = response.body().string();

        ApiParser apiParser = new ApiParser();

        return apiParser.parse(json);

    }


}

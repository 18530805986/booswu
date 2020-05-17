package com.sawapps.baymaxhealthcare.Network.Remote;

import static com.sawapps.baymaxhealthcare.Utils.BASE_URL;



public class ApiUtils {

    private static Service currentService = RetrofitClient.getClient(BASE_URL).create(Service.class);

    public static Service getService() {
        return currentService;
    }
}
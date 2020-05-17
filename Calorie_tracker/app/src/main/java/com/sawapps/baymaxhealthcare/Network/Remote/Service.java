package com.sawapps.baymaxhealthcare.Network.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;


public interface Service {



    @GET
    Call<GetDietResponse> getMeal(@Url String url, @Header("X-Mashape-Key") String s, @Header("X-Mashape-Host") String s2);

}


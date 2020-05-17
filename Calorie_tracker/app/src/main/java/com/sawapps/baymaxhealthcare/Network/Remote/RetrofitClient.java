package com.sawapps.baymaxhealthcare.Network.Remote;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    public static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

       retrofit = new Retrofit.Builder()

                .baseUrl(baseUrl)
               .client(httpClient.build())

               .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
package com.example.android.bakingapp.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        Timber.d("Estoy en ApiClient dentro de Retrofit getClient");
        if (retrofit == null){
            Timber.d("Retrofit  es null");
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL_BASE_JSON_RECIPES)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Timber.d("retrofit es: " + retrofit.toString());
        return retrofit;
    }


}

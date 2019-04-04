package com.canteen.chandan.mcafeteria.model.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL="https://incapacious-capacit.000webhostapp.com/cf_init/";
    private static Retrofit retrofit=null;

    public static Retrofit getRetrofit() {
     if(retrofit==null)
     {
         retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
     }
         return retrofit;
    }

}
